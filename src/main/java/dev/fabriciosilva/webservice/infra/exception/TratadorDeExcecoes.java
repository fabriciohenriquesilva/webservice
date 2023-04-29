package dev.fabriciosilva.webservice.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeExcecoes {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DadosDetalhamentoErro tratarErro404(RegistroNaoEncontradoException ex) {
        return new DadosDetalhamentoErro(ex.getMessage());
    }
}
