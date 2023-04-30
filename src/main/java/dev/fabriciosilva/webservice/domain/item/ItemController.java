package dev.fabriciosilva.webservice.domain.item;

import dev.fabriciosilva.webservice.domain.item.dto.ItemAtualizacao;
import dev.fabriciosilva.webservice.domain.item.dto.ItemForm;
import dev.fabriciosilva.webservice.domain.item.dto.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity<Page<ItemInfo>> listar(@PageableDefault Pageable page) {
        return ResponseEntity.ok(service.listar(page));
    }

    @PostMapping
    public ResponseEntity<ItemInfo> cadastrar(@RequestBody ItemForm form, UriComponentsBuilder uriBuilder) {
        ItemInfo item = service.cadastrar(form);

        URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(item.getId()).toUri();

        return ResponseEntity.created(uri).body(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemInfo> detalhar(@PathVariable Long id) {
        ItemInfo item = service.detalhar(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping
    public ResponseEntity<ItemInfo> atualizar(@RequestBody ItemAtualizacao dados) {
        ItemInfo itemInfo = service.atualizar(dados);
        return ResponseEntity.ok(itemInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}
