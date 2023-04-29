package dev.fabriciosilva.webservice.domain.notafiscal.validations;

import dev.fabriciosilva.webservice.domain.cliente.ClienteRepository;
import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalForm;
import dev.fabriciosilva.webservice.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteExistente implements ValidadorNotaFiscal {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void validar(NotaFiscalForm form) {
        Long clienteId = form.getCliente().getId();
        if (!clienteRepository.existsById(clienteId)) {
            throw new RegistroNaoEncontradoException("O cliente informado não está cadastrado no sistema! ID = " + clienteId);
        }
    }
}
