package dev.fabriciosilva.webservice.domain.produto;

import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoAtualizacao;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoForm;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoInfo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

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

    public Produto(ProdutoForm form) {
        this.codigo = form.getCodigo();
        this.descricao = form.getDescricao();
        this.valorUnitario = form.getValorUnitario();
    }

    public Produto(ProdutoInfo produto) {
        this.id = produto.getId();
        this.codigo = produto.getCodigo();
        this.descricao = produto.getDescricao();
        this.valorUnitario = produto.getValorUnitario();
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void atualizarDados(ProdutoAtualizacao dados) {
        if(dados.getCodigo() != null) {
            this.codigo = dados.getCodigo();
        }
        if(dados.getDescricao() != null) {
            this.descricao = dados.getDescricao();
        }
        if(dados.getValorUnitario() != null) {
            this.valorUnitario = dados.getValorUnitario();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
