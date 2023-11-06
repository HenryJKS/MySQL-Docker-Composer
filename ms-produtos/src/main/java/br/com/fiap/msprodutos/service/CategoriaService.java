package br.com.fiap.msprodutos.service;


import br.com.fiap.msprodutos.dto.CategoriaDTO;
import br.com.fiap.msprodutos.model.Categoria;
import br.com.fiap.msprodutos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        List<Categoria> list = categoriaRepository.findAll();

        return list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado"));

        return new CategoriaDTO(categoria);
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();

        convertDTOtoEntity(categoriaDTO, categoria);
        categoria = categoriaRepository.save(categoria);

        return new CategoriaDTO(categoria);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("ID nao encontrado");
        }
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade");
        }
    }

    private void convertDTOtoEntity(CategoriaDTO categoriaDTO, Categoria categoria) {
        categoria.setNome(categoriaDTO.getNome());
    }

}
