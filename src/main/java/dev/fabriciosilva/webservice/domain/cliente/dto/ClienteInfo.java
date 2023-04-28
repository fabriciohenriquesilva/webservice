package dev.fabriciosilva.webservice.domain.cliente.dto;

import dev.fabriciosilva.webservice.domain.cliente.Cliente;

public class ClienteInfo {

    private Long id;
    private String codigo;
    private String nome;

    public ClienteInfo() {
    }

    public ClienteInfo(Cliente cliente) {
        this.id = cliente.getId();
        this.codigo = cliente.getCodigo();
        this.nome = cliente.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
