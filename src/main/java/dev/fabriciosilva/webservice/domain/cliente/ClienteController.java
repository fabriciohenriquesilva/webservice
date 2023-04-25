package dev.fabriciosilva.webservice.domain.cliente;

import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteAtualizacao;
import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteForm;
import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteInfo;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public ResponseEntity listar(@PageableDefault Pageable page) {

        return ResponseEntity.ok(repository.findAll(page));
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody ClienteForm form, UriComponentsBuilder uriBuilder) {
        Cliente cliente = repository.save(new Cliente(form));

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClienteInfo(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe o cliente de ID: " + id));

        return ResponseEntity.ok(new ClienteInfo(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody ClienteAtualizacao dados) {
        Cliente cliente = repository.findById(dados.getId())
                .orElseThrow(() -> new NoSuchElementException("Não existe o cliente de ID: " + dados.getId()));

        cliente.atualizarDados(dados);

        return ResponseEntity.ok(new ClienteInfo(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
