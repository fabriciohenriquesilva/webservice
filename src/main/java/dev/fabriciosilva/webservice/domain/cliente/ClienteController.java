package dev.fabriciosilva.webservice.domain.cliente;

import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteAtualizacao;
import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteForm;
import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteInfo;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Page<ClienteInfo>> listar(@PageableDefault Pageable page) {
        return ResponseEntity.ok(service.listar(page));
    }

    @PostMapping
    public ResponseEntity<ClienteInfo> cadastrar(@RequestBody ClienteForm form, UriComponentsBuilder uriBuilder) {
        ClienteInfo cliente = service.cadastrar(form);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteInfo> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PutMapping
    public ResponseEntity<ClienteInfo> atualizar(@RequestBody ClienteAtualizacao dados) {
        return ResponseEntity.ok(service.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/notas")
    public ResponseEntity<Page<NotaFiscalInfo>> listarNotasFiscais(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarNotasFiscais(id));
    }

}
