package dev.fabriciosilva.webservice.domain.cliente;

import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteAtualizacao;
import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteForm;
import dev.fabriciosilva.webservice.domain.notafiscal.NotaFiscal;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    @OneToMany(mappedBy = "cliente")
    private List<NotaFiscal> notasFiscais;

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

    public List<NotaFiscal> getNotasFiscais() {
        return Collections.unmodifiableList(this.notasFiscais);
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
