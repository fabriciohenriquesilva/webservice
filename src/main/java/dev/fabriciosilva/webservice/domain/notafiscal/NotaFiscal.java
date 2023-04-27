package dev.fabriciosilva.webservice.domain.notafiscal;

import dev.fabriciosilva.webservice.domain.cliente.Cliente;
import dev.fabriciosilva.webservice.domain.item.Item;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
    private LocalDate data;
    @OneToMany(mappedBy = "notaFiscal")
    private List<Item> itens;
    private BigDecimal valorTotal;

    public NotaFiscal() {
    }

    public NotaFiscal(NotaFiscalForm form) {
        this.numero = form.getNumero();
        this.data = form.getData();
//        this. = form.getData();
    }

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public List<Item> getItens() {
        return itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }
}
