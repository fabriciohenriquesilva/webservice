package dev.fabriciosilva.webservice.domain.notafiscal;

import dev.fabriciosilva.webservice.domain.notafiscal.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/notas")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService service;

    @GetMapping
    public ResponseEntity<Page<NotaFiscalInfo>> listar(@PageableDefault Pageable page) {
        return ResponseEntity.ok(service.listar(page));
    }

    @PostMapping
    public ResponseEntity<NotaFiscalInfo> cadastrar(@RequestBody NotaFiscalForm form, UriComponentsBuilder uriBuilder) {
        NotaFiscalInfo notaFiscalInfo = service.cadastrar(form);

        URI uri = uriBuilder.path("/notas/{id}").buildAndExpand(notaFiscalInfo.getId()).toUri();

        return ResponseEntity.created(uri).body(notaFiscalInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscalInfo> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PutMapping
    public ResponseEntity<NotaFiscalInfo> atualizar(@RequestBody NotaFiscalAtualizacao dados) {
        return ResponseEntity.ok(service.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/remover-item")
    public ResponseEntity removerItem(@RequestBody RemoverItemForm form, @PathVariable Long id) {
        service.removerItem(id, form);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/adicionar-item")
    public ResponseEntity<NotaFiscalInfo> adicionarItem(@RequestBody AdicionarItemForm form, @PathVariable Long id) {
        return ResponseEntity.ok(service.adicionarItem(id, form));
    }
}
