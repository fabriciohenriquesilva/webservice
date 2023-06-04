package dev.fabriciosilva.webservice.domain.notafiscal.dto;

import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteInfo;
import dev.fabriciosilva.webservice.domain.item.dto.ItemForm;

import java.time.LocalDate;
import java.util.List;

public class NotaFiscalAtualizacao {

    private Long id;
    private Long numero;
    private ClienteInfo cliente;
    private LocalDate data;
    private List<ItemForm> itens;

    public Long getId() {
        return id;
    }

    public Long getNumero() {
        return numero;
    }

    public ClienteInfo getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public List<ItemForm> getItens() {
        return itens;
    }
}
