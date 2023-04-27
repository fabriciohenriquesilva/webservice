package dev.fabriciosilva.webservice.domain.notafiscal.dto;

import dev.fabriciosilva.webservice.domain.item.dto.ItemInfo;

import java.time.LocalDate;
import java.util.List;

public class NotaFiscalForm {

    private Integer numero;
    private Long clienteId;
    private LocalDate data;
    private List<ItemInfo> itens;

    public NotaFiscalForm() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemInfo> getItens() {
        return itens;
    }

    public void setItens(List<ItemInfo> itens) {
        this.itens = itens;
    }
}
