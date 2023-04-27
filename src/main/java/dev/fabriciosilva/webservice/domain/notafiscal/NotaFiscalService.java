package dev.fabriciosilva.webservice.domain.notafiscal;

import dev.fabriciosilva.webservice.domain.cliente.ClienteRepository;
import dev.fabriciosilva.webservice.domain.item.ItemRepository;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalAtualizacao;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalForm;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalInfo;
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

    public Page<NotaFiscalInfo> listar(Pageable page) {
        return notaFiscalRepository.findAll(page).map(NotaFiscalInfo::new);
    }

    @Transactional
    public NotaFiscalInfo cadastrar(NotaFiscalForm form) {
         if ( !clienteRepository.existsById(form.getClienteId()) ) {
             throw new NoSuchElementException("Não existe o cliente de ID: " + form.getClienteId());
         }

         form.getItens().forEach(i -> itemRepository.existsById(i.getId()));

         NotaFiscal notaFiscal = new NotaFiscal(form);

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
}
