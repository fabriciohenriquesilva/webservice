package dev.fabriciosilva.webservice.domain.notafiscal.dto;

import dev.fabriciosilva.webservice.domain.item.dto.ItemAtualizacao;

import java.time.LocalDate;
import java.util.List;

public class NotaFiscalAtualizacao {

    private Long id;
    private Long numero;
    private Long clienteId;
    private LocalDate data;
    private List<ItemAtualizacao> itens;

    public Long getId() {
        return id;
    }

    public Long getNumero() {
        return numero;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public LocalDate getData() {
        return data;
    }

    public List<ItemAtualizacao> getItens() {
        return itens;
    }
}
