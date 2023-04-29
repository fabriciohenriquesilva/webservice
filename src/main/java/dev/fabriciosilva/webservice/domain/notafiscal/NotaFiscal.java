package dev.fabriciosilva.webservice.domain.notafiscal;

import dev.fabriciosilva.webservice.domain.cliente.Cliente;
import dev.fabriciosilva.webservice.domain.item.Item;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalAtualizacao;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero;
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
    private LocalDate data;
    @OneToMany(mappedBy = "notaFiscal", orphanRemoval = true)
    private List<Item> itens;
    private BigDecimal valorTotal;

    public NotaFiscal() {
    }

    public NotaFiscal(NotaFiscalForm form) {
        this.numero = form.getNumero();
        this.data = form.getData();
    }

    public Long getId() {
        return id;
    }

    public Long getNumero() {
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(Item item) {
        if(this.itens == null) {
            this.itens = new ArrayList<>();
        }
        this.itens.add(item);
    }

    public void calcularValorTotal() {
        this.valorTotal = this.itens.stream()
                .map(i -> i.getValorTotal())
                .reduce(BigDecimal::add).get();
    }

    public void atualizarDados(NotaFiscalAtualizacao dados) {
        if(dados.getNumero() != null) {
            this.numero = dados.getNumero();
        }
        if(dados.getData() != null) {
            this.data = dados.getData();
        }
    }

}
