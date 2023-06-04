package dev.fabriciosilva.webservice.domain.item;

import dev.fabriciosilva.webservice.domain.item.dto.ItemAtualizacao;
import dev.fabriciosilva.webservice.domain.item.dto.ItemForm;
import dev.fabriciosilva.webservice.domain.notafiscal.NotaFiscal;
import dev.fabriciosilva.webservice.domain.produto.Produto;
import dev.fabriciosilva.webservice.infra.exception.DadosValidacaoException;

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

    public Item(Integer numeroSequencial, Produto produto, Integer quantidade, NotaFiscal notaFiscal) {
        this.numeroSequencial = numeroSequencial;
        this.produto = produto;
        this.quantidade = quantidade;
        this.notaFiscal = notaFiscal;
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
        if(this.valorTotal == null) {
            calculaValorTotal();
        }
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

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void calculaValorTotal() {
        if (this.produto == null) {
            throw new NoSuchElementException("Produto não foi informado");
        }

        this.valorTotal = this.produto
                .getValorUnitario()
                .multiply(new BigDecimal(this.quantidade));

//        this.notaFiscal.calcularValorTotal();
    }

    public void atualizarDados(ItemAtualizacao dados) {
        if (dados.getNumeroSequencial() != null) {
            this.numeroSequencial = dados.getNumeroSequencial();
        }
        if (dados.getQuantidade() != null) {
            this.quantidade = dados.getQuantidade();
        }
        calculaValorTotal();
    }

    public void atualizarDados(ItemForm dados) {
        if (!this.quantidade.equals(dados.getQuantidade())) {
            this.quantidade = dados.getQuantidade();
        }
        if (!this.numeroSequencial.equals(dados.getNumeroSequencial())) {
            this.numeroSequencial = dados.getNumeroSequencial();
        }
        if (!this.valorTotal.equals(dados.getValorTotal())) {
            this.valorTotal = dados.getValorTotal();
        }
    }

    public void subtrairQuantidade(Integer quantidade) {
        if(quantidade > this.quantidade) {
            throw new DadosValidacaoException("Quantidade a ser removida é maior do que a presente na nota fiscal!");
        }
        this.quantidade -= quantidade;
        calculaValorTotal();
    }

    public void somarQuantidade(Integer quantidade) {
        if(quantidade <= 0) {
            throw new DadosValidacaoException("Quantidade a ser adicionada deve ser maior que 0!");
        }
        this.quantidade += quantidade;
        calculaValorTotal();
    }
}
