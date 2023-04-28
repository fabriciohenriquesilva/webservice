package dev.fabriciosilva.webservice.domain.notafiscal.dto;

import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteInfo;
import dev.fabriciosilva.webservice.domain.item.dto.ItemInfo;
import dev.fabriciosilva.webservice.domain.notafiscal.NotaFiscal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class NotaFiscalInfo {

    private Long id;
    private Integer numero;
    private ClienteInfo cliente;
    private LocalDate data;
    private List<ItemInfo> itens;

    private BigDecimal valorTotal;

    public NotaFiscalInfo() {
    }

    public NotaFiscalInfo(NotaFiscal notaFiscal) {
        this.id = notaFiscal.getId();
        this.numero = notaFiscal.getNumero();
        this.cliente = new ClienteInfo(notaFiscal.getCliente());
        this.data = notaFiscal.getData();
        this.itens = notaFiscal.getItens()
                .stream().map(ItemInfo::new).collect(Collectors.toList());
        this.valorTotal = notaFiscal.getValorTotal();
    }

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public ClienteInfo getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public List<ItemInfo> getItens() {
        return itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
