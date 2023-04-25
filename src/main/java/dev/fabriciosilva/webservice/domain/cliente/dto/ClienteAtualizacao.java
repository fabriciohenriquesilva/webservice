package dev.fabriciosilva.webservice.domain.cliente.dto;

public class ClienteAtualizacao {

    private Long id;
    private String codigo;
    private String nome;

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
