package dev.fabriciosilva.webservice.domain.item;

import dev.fabriciosilva.webservice.domain.item.dto.ItemAtualizacao;
import dev.fabriciosilva.webservice.domain.item.dto.ItemForm;
import dev.fabriciosilva.webservice.domain.item.dto.ItemInfo;
import dev.fabriciosilva.webservice.domain.produto.Produto;
import dev.fabriciosilva.webservice.domain.produto.ProdutoRepository;
import dev.fabriciosilva.webservice.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public Page<ItemInfo> listar(Pageable page) {
        return itemRepository.findAll(page).map(ItemInfo::new);
    }

    @Transactional
    public ItemInfo cadastrar(ItemForm form) {

        Produto produto = buscarProduto(form.getProduto().getId());
        Item item = new Item(form);
        item.setProduto(produto);
        item.calculaValorTotal();

        itemRepository.save(item);

        return new ItemInfo(item);
    }

    public ItemInfo detalhar(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe o item de ID: " + id));

        return new ItemInfo(item);
    }

    @Transactional
    public ItemInfo atualizar(ItemAtualizacao dados) {

        Item item = itemRepository.findById(dados.getId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe o item de ID: " + dados.getId()));

        if (dados.getProdutoId() != null) {
            Produto produto = buscarProduto(dados.getProdutoId());
            item.setProduto(produto);
        }

        item.atualizarDados(dados);

        return new ItemInfo(item);
    }

    @Transactional
    public void remover(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Item informado não existe! Favor conferir dados"));

        itemRepository.delete(item);
    }

    private Produto buscarProduto(Long produtoId) {
        if (!produtoRepository.existsById(produtoId)) {
            throw new RegistroNaoEncontradoException("Produto informado não existe! Favor conferir dados!");
        }
        return produtoRepository.getReferenceById(produtoId);
    }
}
