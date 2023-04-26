package dev.fabriciosilva.webservice.domain.produto;

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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity listar(@PageableDefault Pageable page) {

        return ResponseEntity.ok(
                repository.findAll(page)
                        .map(ProdutoInfo::new)
        );
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody ProdutoForm form, UriComponentsBuilder uriBuilder) {
        Produto produto = repository.save(new Produto(form));

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProdutoInfo(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe o produto de ID: " + id));

        return ResponseEntity.ok(new ProdutoInfo(produto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody ProdutoAtualizacao dados) {
        Produto produto = repository.findById(dados.getId())
                .orElseThrow(() -> new NoSuchElementException("Não existe o produto de ID: " + dados.getId()));

        produto.atualizarDados(dados);

        return ResponseEntity.ok(new ProdutoInfo(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
