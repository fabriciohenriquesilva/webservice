package dev.fabriciosilva.webservice.domain.notafiscal;

import dev.fabriciosilva.webservice.domain.cliente.Cliente;
import dev.fabriciosilva.webservice.domain.cliente.ClienteRepository;
import dev.fabriciosilva.webservice.domain.item.Item;
import dev.fabriciosilva.webservice.domain.item.ItemRepository;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.*;
import dev.fabriciosilva.webservice.domain.notafiscal.validations.ValidadorNotaFiscal;
import dev.fabriciosilva.webservice.domain.produto.Produto;
import dev.fabriciosilva.webservice.domain.produto.ProdutoRepository;
import dev.fabriciosilva.webservice.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private List<ValidadorNotaFiscal> validacoes;

    public Page<NotaFiscalInfo> listar(Pageable page) {
        return notaFiscalRepository.findAll(page).map(NotaFiscalInfo::new);
    }

    @Transactional
    public NotaFiscalInfo cadastrar(NotaFiscalForm form) {

        validacoes.forEach(v -> v.validar(form));

        Long clienteId = form.getCliente().getId();
        Cliente cliente = clienteRepository.getReferenceById(clienteId);

        NotaFiscal notaFiscal = new NotaFiscal(form);
        notaFiscal.setCliente(cliente);
        notaFiscalRepository.save(notaFiscal);

        cadastrarItens(form, notaFiscal);

        notaFiscal.calcularValorTotal();

        return new NotaFiscalInfo(notaFiscal);
    }

    public NotaFiscalInfo detalhar(Long id) {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("A Nota Fiscal informada não está cadastrada no sistema! ID = " + id));

        return new NotaFiscalInfo(notaFiscal);
    }

    @Transactional
    public NotaFiscalInfo atualizar(NotaFiscalAtualizacao dados) {
        Long notaFiscalId = dados.getId();
        NotaFiscal notaFiscal = notaFiscalRepository.findById(notaFiscalId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("A Nota Fiscal informada não está cadastrada no sistema! ID = " + notaFiscalId));

        if (dados.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dados.getClienteId())
                    .orElseThrow(() -> new RegistroNaoEncontradoException("O cliente informado não está cadastrado no sistema! ID = " + dados.getClienteId()));
            notaFiscal.setCliente(cliente);
        }

        dados.getItens().forEach(itemAtualizacao -> {
            Item item = itemRepository.findById(itemAtualizacao.getId())
                    .orElseThrow(() -> new RegistroNaoEncontradoException("O item informado não está presente na nota fiscal! ID = " + itemAtualizacao.getId()));

            item.atualizarDados(itemAtualizacao);

            Long produtoId = itemAtualizacao.getProdutoId();
            if (produtoId != null) {
                Produto produto = produtoRepository.findById(produtoId)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("O produto informado não está cadastrado no sistema! ID = " + produtoId));
                item.setProduto(produto);
                item.calculaValorTotal();
            }
        });

        notaFiscal.atualizarDados(dados);
        notaFiscal.calcularValorTotal();

        return new NotaFiscalInfo(notaFiscal);
    }

    @Transactional
    public void remover(Long id) {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("A Nota Fiscal informada não está cadastrada no sistema! ID = " + id));

        notaFiscalRepository.delete(notaFiscal);
    }

    @Transactional
    private void cadastrarItens(NotaFiscalForm form, NotaFiscal notaFiscal) {
        form.getItens().forEach(itemForm -> {

            Long produtoId = itemForm.getProduto().getId();
            Produto produto = produtoRepository.getReferenceById(produtoId);

            Item item = new Item(itemForm);
            item.setProduto(produto);
            item.setNotaFiscal(notaFiscal);
            item.calculaValorTotal();

            itemRepository.save(item);
            notaFiscal.adicionarItem(item);
        });
    }

    @Transactional
    public void removerItem(Long notaFiscalId, RemoverItemForm form) {
        Long itemId = form.getId();
        Integer quantidade = form.getQuantidade();

        if (!itemRepository.itemPertenceANotaFiscal(notaFiscalId, itemId)) {
            throw new RegistroNaoEncontradoException("A nota fiscal não possui esse item");
        }

        Item item = itemRepository.findById(itemId).get();
        item.subtrairQuantidade(quantidade);

        if (item.getQuantidade() == 0) {
            itemRepository.removerItemDaNotaFiscal(notaFiscalId, itemId);
        }

    }

    @Transactional
    public NotaFiscalInfo adicionarItem(Long notaFiscalId, AdicionarItemForm form) {
        Long produtoId = form.getProdutoId();
        Integer quantidade = form.getQuantidade();

        if (!notaFiscalRepository.existsById(notaFiscalId)) {
            throw new RegistroNaoEncontradoException("Nota Fiscal informada não está cadastrada no sistema!");
        }

        if (!produtoRepository.existsById(produtoId)) {
            throw new RegistroNaoEncontradoException("Produto informado não está cadastrado no sistema!");
        }

        Produto produto = produtoRepository.findById(produtoId).get();

        NotaFiscal notaFiscal = notaFiscalRepository.findById(notaFiscalId).get();
        Optional<Item> optional = notaFiscal.getItens().stream()
                .filter(i -> i.getProduto().equals(produto))
                .findFirst();

        if (optional.isPresent()) {
            Item item = optional.get();
            item.somarQuantidade(quantidade);
        } else {
            Item item = new Item(null, produto, quantidade, notaFiscal);

            itemRepository.save(item);
            notaFiscal.adicionarItem(item);
        }

        return new NotaFiscalInfo(notaFiscal);
    }
}
