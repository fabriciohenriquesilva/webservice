package dev.fabriciosilva.webservice.domain.cliente;

import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteAtualizacao;
import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteForm;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;

    public Cliente() {
    }

    public Cliente(ClienteForm form) {
        this.codigo = form.getCodigo();
        this.nome = form.getNome();
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

    public void atualizarDados(ClienteAtualizacao dados) {
        if(dados.getCodigo() != null) {
            this.codigo = dados.getCodigo();
        }
        if(dados.getNome() != null) {
            this.nome = dados.getNome();
        }
    }
}
