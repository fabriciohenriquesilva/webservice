package dev.fabriciosilva.webservice.domain.item;

import dev.fabriciosilva.webservice.domain.item.dto.ItemForm;
import dev.fabriciosilva.webservice.domain.item.dto.ItemInfo;
import dev.fabriciosilva.webservice.domain.produto.Produto;
import dev.fabriciosilva.webservice.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ItemInfo cadastrar(ItemForm form) {

        Long produtoId = form.getProduto().getId();

        if (!produtoRepository.existsById(produtoId)) {
            throw new NoSuchElementException("Produto informado não existe! Favor conferir dados!");
        }

        Produto produto = produtoRepository.getReferenceById(produtoId);
        Item item = new Item(form);
        item.setProduto(produto);
        item.calculaValorTotal();

        System.out.println(item);
        itemRepository.save(item);

        return new ItemInfo(item);
    }
}
