package br.com.fiap.msprodutos.service;

import br.com.fiap.msprodutos.dto.ProdutoCategoriaDTO;
import br.com.fiap.msprodutos.dto.ProdutoDTO;
import br.com.fiap.msprodutos.model.Categoria;
import br.com.fiap.msprodutos.model.Produto;
import br.com.fiap.msprodutos.repository.CategoriaRepository;
import br.com.fiap.msprodutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll() {
        List<Produto> list = produtoRepository.findAll();

        return list.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProdutoCategoriaDTO> findAllWithCategoriaNome(){
        List<Produto> list = produtoRepository.findAll();

        return list.stream().map(ProdutoCategoriaDTO::new).collect(Collectors.toList());
    }

    public ProdutoCategoriaDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso nao encontrado"));
        return new ProdutoCategoriaDTO(produto);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        copyDtoToEntity(produtoDTO, produto);
        produto = produtoRepository.save(produto);

        return new ProdutoDTO(produto);
    }

    private void copyDtoToEntity(ProdutoDTO produtoDTO, Produto produto) {
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCaracteristica(produtoDTO.getCaracteristica());
        Categoria categoria = new Categoria();
        categoria = categoriaRepository.findById(produtoDTO.getCategoriaId()).orElseThrow(
                () -> new EntityNotFoundException("Categoria ID")
        );

        produto.setCategoria(categoria);
    }
}
