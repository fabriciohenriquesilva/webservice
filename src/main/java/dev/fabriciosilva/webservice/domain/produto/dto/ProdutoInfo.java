package dev.fabriciosilva.webservice.domain.produto.dto;

import dev.fabriciosilva.webservice.domain.produto.Produto;

import java.math.BigDecimal;

public class ProdutoInfo {

    private Long id;
    private String codigo;
    private String descricao;
    private BigDecimal valorUnitario;

    public ProdutoInfo() {
    }

    public ProdutoInfo(Produto produto) {
        this.id = produto.getId();
        this.codigo = produto.getCodigo();
        this.descricao = produto.getDescricao();
        this.valorUnitario = produto.getValorUnitario();
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }
}
