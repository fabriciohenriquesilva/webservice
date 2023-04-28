package dev.fabriciosilva.webservice.domain.notafiscal.dto;

import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteInfo;
import dev.fabriciosilva.webservice.domain.item.dto.ItemForm;
import dev.fabriciosilva.webservice.domain.item.dto.ItemInfo;

import java.time.LocalDate;
import java.util.List;

public class NotaFiscalForm {

    private Integer numero;
    private ClienteInfo cliente;
    private LocalDate data;
    private List<ItemForm> itens;

    public NotaFiscalForm() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public ClienteInfo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteInfo cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemForm> getItens() {
        return itens;
    }

    public void setItens(List<ItemForm> itens) {
        this.itens = itens;
    }
}
