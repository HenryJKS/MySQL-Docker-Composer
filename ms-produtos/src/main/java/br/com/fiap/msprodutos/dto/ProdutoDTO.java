package br.com.fiap.msprodutos.dto;

import br.com.fiap.msprodutos.model.Produto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String caracteristica;
    private Long categoriaId;

    public ProdutoDTO(long id, String nome, String descricao, BigDecimal preco, String caracteristica, Long categoriaId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.caracteristica = caracteristica;
        this.categoriaId = categoriaId;
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.caracteristica = produto.getCaracteristica();
        this.categoriaId = produto.getCategoria().getId();
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }
}
