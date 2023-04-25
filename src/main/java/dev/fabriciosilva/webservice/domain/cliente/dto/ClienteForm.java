package dev.fabriciosilva.webservice.domain.cliente.dto;

public class ClienteForm {

    private String codigo;
    private String nome;

    public ClienteForm() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
