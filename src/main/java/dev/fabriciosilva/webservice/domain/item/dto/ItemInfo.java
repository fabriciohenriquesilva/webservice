package dev.fabriciosilva.webservice.domain.item.dto;

import dev.fabriciosilva.webservice.domain.item.Item;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalProdutoInfo;

import java.math.BigDecimal;

public class ItemInfo {

    private Long id;
    private Integer numeroSequencial;
    private NotaFiscalProdutoInfo produto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private Long notaFiscal;

    public ItemInfo() {
    }

    public ItemInfo(Item item) {
        this.id = item.getId();
        this.numeroSequencial = item.getNumeroSequencial();
        this.produto = new NotaFiscalProdutoInfo(item.getProduto());
        this.quantidade = item.getQuantidade();
        this.valorTotal = item.getValorTotal();
        this.notaFiscal = item.getNotaFiscal().getNumero();
        this.valorUnitario = item.getValorTotal().divide(new BigDecimal(item.getQuantidade()));
    }

    public Long getId() {
        return id;
    }

    public Integer getNumeroSequencial() {
        return numeroSequencial;
    }

    public NotaFiscalProdutoInfo getProduto() {
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

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
