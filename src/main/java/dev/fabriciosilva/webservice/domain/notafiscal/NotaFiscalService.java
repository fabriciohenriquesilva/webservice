package dev.fabriciosilva.webservice.domain.notafiscal;

import dev.fabriciosilva.webservice.domain.cliente.Cliente;
import dev.fabriciosilva.webservice.domain.cliente.ClienteRepository;
import dev.fabriciosilva.webservice.domain.item.Item;
import dev.fabriciosilva.webservice.domain.item.ItemRepository;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalAtualizacao;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalForm;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalInfo;
import dev.fabriciosilva.webservice.domain.produto.Produto;
import dev.fabriciosilva.webservice.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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

    public Page<NotaFiscalInfo> listar(Pageable page) {
        return notaFiscalRepository.findAll(page).map(NotaFiscalInfo::new);
    }

    @Transactional
    public NotaFiscalInfo cadastrar(NotaFiscalForm form) {
        if (!clienteRepository.existsById(form.getCliente().getId())) {
            throw new NoSuchElementException("Não existe o cliente de ID: " + form.getCliente().getId());
        }

        Cliente cliente = clienteRepository.getReferenceById(form.getCliente().getId());
        NotaFiscal notaFiscal = new NotaFiscal(form);
        notaFiscal.setCliente(cliente);
        notaFiscalRepository.save(notaFiscal);
        adicionarItem(form, notaFiscal);

        notaFiscal.getItens().forEach(i -> i.setNotaFiscal(notaFiscal));
        notaFiscal.calcularValorTotal();

        return new NotaFiscalInfo(notaFiscal);
    }

    public NotaFiscalInfo detalhar(Long id) {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe a nota fiscal de ID: " + id));

        return new NotaFiscalInfo(notaFiscal);
    }

    @Transactional
    public NotaFiscalInfo atualizar(NotaFiscalAtualizacao dados) {
        return null;
    }

    @Transactional
    public void remover(Long id) {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe a nota fiscal de ID: " + id));

        notaFiscalRepository.delete(notaFiscal);
    }

    @Transactional
    private void adicionarItem(NotaFiscalForm form, NotaFiscal notaFiscal) {
        form.getItens().forEach(itemForm -> {
            Long id = itemForm.getProduto().getId();

            if(!produtoRepository.existsById(id)) {
                throw new NoSuchElementException("Não existe a nota fiscal de ID: " + id);
            }
            Produto produto = produtoRepository.getReferenceById(id);
            Item item = new Item(itemForm);
            item.setProduto(produto);
            item.setNotaFiscal(notaFiscal);
            item.calculaValorTotal();
            notaFiscal.adicionarItem(item);
            itemRepository.save(item);
        });
    }
}
