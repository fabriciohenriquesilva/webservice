package dev.fabriciosilva.webservice.domain.produto;

import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoAtualizacao;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoForm;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoInfo;
import dev.fabriciosilva.webservice.infra.exception.RegistroNaoEncontradoException;
import dev.fabriciosilva.webservice.infra.exception.RegistroPossuiVinculoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<ProdutoInfo> listar(Pageable page) {
        return produtoRepository.findAll(page)
                .map(ProdutoInfo::new);
    }

    @Transactional
    public ProdutoInfo cadastrar(ProdutoForm form) {
        Produto produto = produtoRepository.save(new Produto(form));
        return new ProdutoInfo(produto);
    }

    public ProdutoInfo detalhar(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("O produto informado não está cadastrado no sistema! ID = : " + id));

        return new ProdutoInfo(produto);
    }

    @Transactional
    public ProdutoInfo atualizar(ProdutoAtualizacao dados) {
        Produto produto = produtoRepository.findById(dados.getId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("O produto informado não está cadastrado no sistema! ID = : " + dados.getId()));

        produto.atualizarDados(dados);

        return new ProdutoInfo(produto);
    }

    @Transactional
    public void remover(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException("O produto informado não está cadastrado no sistema! ID = : " + id);
        }

        try {
            produtoRepository.deleteById(id);
            produtoRepository.flush();
        }
        catch (RuntimeException ex) {
            throw new RegistroPossuiVinculoException("Registro possui vínculo com outra entidade! Exclusão impossível!");
        }
    }
}
