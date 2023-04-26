package dev.fabriciosilva.webservice.domain.item.dto;

import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoInfo;

import java.math.BigDecimal;

public class ItemAtualizacao {

    private Long id;
    private Integer numeroSequencial;
    private ProdutoInfo produto;
    private Integer quantidade;
    private BigDecimal valorTotal;

    public Long getId() {
        return id;
    }

    public Integer getNumeroSequencial() {
        return numeroSequencial;
    }

    public ProdutoInfo getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
