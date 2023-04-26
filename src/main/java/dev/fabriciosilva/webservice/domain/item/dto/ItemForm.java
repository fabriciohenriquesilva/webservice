package dev.fabriciosilva.webservice.domain.item.dto;

import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoInfo;

import java.math.BigDecimal;

public class ItemForm {

    private Integer numeroSequencial;
    private ProdutoInfo produto;
    private Integer quantidade;
    private BigDecimal valorTotal;

    public Integer getNumeroSequencial() {
        return numeroSequencial;
    }

    public void setNumeroSequencial(Integer numeroSequencial) {
        this.numeroSequencial = numeroSequencial;
    }

    public ProdutoInfo getProduto() {
        return produto;
    }

    public void setProduto(ProdutoInfo produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
