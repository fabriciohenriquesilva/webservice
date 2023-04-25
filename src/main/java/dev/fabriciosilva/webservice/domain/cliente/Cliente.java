package dev.fabriciosilva.webservice.domain.cliente;

import dev.fabriciosilva.webservice.domain.notafiscal.NotaFiscal;

import javax.persistence.*;
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

}
