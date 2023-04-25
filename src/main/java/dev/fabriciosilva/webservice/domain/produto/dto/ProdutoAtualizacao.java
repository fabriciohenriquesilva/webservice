package dev.fabriciosilva.webservice.domain.produto.dto;

import java.math.BigDecimal;

public class ProdutoAtualizacao {

    private Long id;
    private String codigo;
    private String descricao;
    private BigDecimal valorUnitario;

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
