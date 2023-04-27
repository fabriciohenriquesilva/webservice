package dev.fabriciosilva.webservice.domain.item;

import dev.fabriciosilva.webservice.domain.item.dto.ItemAtualizacao;
import dev.fabriciosilva.webservice.domain.item.dto.ItemForm;
import dev.fabriciosilva.webservice.domain.notafiscal.NotaFiscal;
import dev.fabriciosilva.webservice.domain.produto.Produto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

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

    public Item(ItemForm form) {
        this.numeroSequencial = form.getNumeroSequencial();
        this.quantidade = form.getQuantidade();
    }

    public Long getId() {
        return id;
    }

    public Integer getNumeroSequencial() {
        return numeroSequencial;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public void calculaValorTotal() {
        if (this.produto == null) {
            throw new NoSuchElementException("Produto indefinido");
        }

        this.valorTotal = this.produto
                .getValorUnitario()
                .multiply(new BigDecimal(this.quantidade));
    }

    public void atualizarDados(ItemAtualizacao dados) {
        if(dados.getNumeroSequencial() != null) {
            this.numeroSequencial = dados.getNumeroSequencial();
        }
        if(dados.getQuantidade() != null) {
            this.quantidade = dados.getQuantidade();
        }
        if(dados.getProduto() != null) {
            this.produto = new Produto(dados.getProduto());
        }

        calculaValorTotal();
    }
}
