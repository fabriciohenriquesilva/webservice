package dev.fabriciosilva.webservice.domain.cliente;

import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteAtualizacao;
import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteForm;
import dev.fabriciosilva.webservice.domain.cliente.dto.ClienteInfo;
import dev.fabriciosilva.webservice.domain.notafiscal.NotaFiscalRepository;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalInfo;
import dev.fabriciosilva.webservice.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public Page<ClienteInfo> listar(Pageable page) {
        return clienteRepository.findAll(page).map(ClienteInfo::new);
    }

    @Transactional
    public ClienteInfo cadastrar(ClienteForm form) {
        Cliente cliente = clienteRepository.save(new Cliente(form));

        return new ClienteInfo(cliente);
    }

    public ClienteInfo detalhar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("O cliente informado não está cadastrado no sistema! ID = " + id));

        return new ClienteInfo(cliente);
    }

    @Transactional
    public ClienteInfo atualizar(ClienteAtualizacao dados) {
        Cliente cliente = clienteRepository.findById(dados.getId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("O cliente informado não está cadastrado no sistema! ID = " + dados.getId()));

        cliente.atualizarDados(dados);

        return new ClienteInfo(cliente);
    }

    @Transactional
    public void remover(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException("O cliente informado não está cadastrado no sistema! ID = " + id);
        }
        clienteRepository.deleteById(id);
    }

    public Page<NotaFiscalInfo> listarNotasFiscais(Long id, Pageable page) {
//        Pageable page = PageRequest.of(0, 10);
        return notaFiscalRepository.findByClienteId(id, page);
    }
}
