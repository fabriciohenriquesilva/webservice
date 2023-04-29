package dev.fabriciosilva.webservice.domain.item.dto;

import dev.fabriciosilva.webservice.domain.item.Item;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoInfo;

import java.math.BigDecimal;

public class ItemInfo {

    private Long id;
    private Integer numeroSequencial;
    private ProdutoInfo produto;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private Long notaFiscal;

    public ItemInfo() {
    }

    public ItemInfo(Item item) {
        this.id = item.getId();
        this.numeroSequencial = item.getNumeroSequencial();
        this.produto = new ProdutoInfo(item.getProduto());
        this.quantidade = item.getQuantidade();
        this.valorTotal = item.getValorTotal();
        this.notaFiscal = item.getNotaFiscal().getNumero();
    }

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

    public Long getNotaFiscal() {
        return notaFiscal;
    }
}
