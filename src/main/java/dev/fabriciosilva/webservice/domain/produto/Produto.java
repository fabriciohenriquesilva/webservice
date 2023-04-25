package dev.fabriciosilva.webservice.domain.produto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String descricao;
    private BigDecimal valorUnitario;

    public Produto() {
    }
}
