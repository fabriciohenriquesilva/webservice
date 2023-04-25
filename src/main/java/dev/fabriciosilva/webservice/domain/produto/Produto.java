package dev.fabriciosilva.webservice.domain.produto;

import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoAtualizacao;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoForm;

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

    public Produto(ProdutoForm form) {
        this.codigo = form.getCodigo();
        this.descricao = form.getDescricao();
        this.valorUnitario = form.getValorUnitario();
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
}
