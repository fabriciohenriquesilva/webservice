package dev.fabriciosilva.webservice.domain.produto;

import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoAtualizacao;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoForm;
import dev.fabriciosilva.webservice.domain.produto.dto.ProdutoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoInfo>> listar(@PageableDefault Pageable page) {
        return ResponseEntity.ok(service.listar(page));
    }

    @PostMapping
    public ResponseEntity<ProdutoInfo> cadastrar(@RequestBody ProdutoForm form, UriComponentsBuilder uriBuilder) {
        ProdutoInfo produtoInfo = service.cadastrar(form);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoInfo.getId()).toUri();

        return ResponseEntity.created(uri).body(produtoInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoInfo> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PutMapping
    public ResponseEntity<ProdutoInfo> atualizar(@RequestBody ProdutoAtualizacao dados) {
        return ResponseEntity.ok(service.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
