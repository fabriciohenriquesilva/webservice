package dev.fabriciosilva.webservice.domain.produto.dto;

import java.math.BigDecimal;

public class ProdutoForm {

    private String codigo;
    private String descricao;
    private BigDecimal valorUnitario;

    public ProdutoForm() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
