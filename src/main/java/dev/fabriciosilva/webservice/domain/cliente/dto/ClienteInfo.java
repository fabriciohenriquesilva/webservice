package dev.fabriciosilva.webservice.domain.cliente.dto;

import dev.fabriciosilva.webservice.domain.cliente.Cliente;

public class ClienteInfo {

    private String codigo;
    private String nome;
    public ClienteInfo(Cliente cliente) {
        this.codigo = cliente.getCodigo();
        this.nome = cliente.getNome();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
