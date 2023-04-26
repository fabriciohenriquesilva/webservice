package dev.fabriciosilva.webservice.domain.item;

import dev.fabriciosilva.webservice.domain.item.dto.ItemForm;
import dev.fabriciosilva.webservice.domain.item.dto.ItemInfo;
import dev.fabriciosilva.webservice.domain.produto.Produto;
import dev.fabriciosilva.webservice.domain.produto.ProdutoRepository;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoAtualizacao;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoForm;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity listar(@PageableDefault Pageable page) {

        return ResponseEntity.ok(
                repository.findAll(page)
                        .map(ItemInfo::new)
        );
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody ItemForm form, UriComponentsBuilder uriBuilder) {
//        Produto produto = repository.save(new Item(form));
        ItemInfo item = service.cadastrar(form);

        URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(item.getId()).toUri();

        return ResponseEntity.created(uri).body(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe o produto de ID: " + id));

        return ResponseEntity.ok(new ItemInfo(item));
    }

}
