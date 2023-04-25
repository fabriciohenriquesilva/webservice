package dev.fabriciosilva.webservice.domain.item;

import dev.fabriciosilva.webservice.domain.notafiscal.NotaFiscal;
import dev.fabriciosilva.webservice.domain.produto.Produto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroSequencial;
    @OneToOne
    private Produto produto;
    private Integer quantidade;
    private BigDecimal valorTotal;
    @ManyToOne
    @JoinColumn(name = "fk_nota_fiscal")
    private NotaFiscal notaFiscal;

    public Item() {
    }


}
