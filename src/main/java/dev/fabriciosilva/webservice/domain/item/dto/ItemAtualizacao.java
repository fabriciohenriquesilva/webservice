package dev.fabriciosilva.webservice.domain.item.dto;

import java.math.BigDecimal;

public class ItemAtualizacao {

    private Long id;
    private Integer numeroSequencial;
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal valorTotal;

    public Long getId() {
        return id;
    }

    public Integer getNumeroSequencial() {
        return numeroSequencial;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
