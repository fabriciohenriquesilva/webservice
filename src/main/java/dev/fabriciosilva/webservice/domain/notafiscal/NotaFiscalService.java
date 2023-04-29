package dev.fabriciosilva.webservice.domain.notafiscal;

import dev.fabriciosilva.webservice.domain.cliente.Cliente;
import dev.fabriciosilva.webservice.domain.cliente.ClienteRepository;
import dev.fabriciosilva.webservice.domain.item.Item;
import dev.fabriciosilva.webservice.domain.item.ItemRepository;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalAtualizacao;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalForm;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalInfo;
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

        adicionarItem(form, notaFiscal);

        notaFiscal.calcularValorTotal();

        return new NotaFiscalInfo(notaFiscal);
    }

    public NotaFiscalInfo detalhar(Long id) {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe a nota fiscal de ID: " + id));

        return new NotaFiscalInfo(notaFiscal);
    }

    @Transactional
    public NotaFiscalInfo atualizar(NotaFiscalAtualizacao dados) {
        return null;
    }

    @Transactional
    public void remover(Long id) {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("A Nota Fiscal informada não está cadastrada no sistema! ID = " + id));

        notaFiscalRepository.delete(notaFiscal);
    }

    @Transactional
    private void adicionarItem(NotaFiscalForm form, NotaFiscal notaFiscal) {
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
}
