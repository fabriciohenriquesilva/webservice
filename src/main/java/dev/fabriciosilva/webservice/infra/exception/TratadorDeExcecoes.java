package dev.fabriciosilva.webservice.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(DadosValidacaoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity tratarErroDeFormulario(DadosValidacaoException ex) {
        return ResponseEntity.badRequest().body(new DadosDetalhamentoErro(ex.getMessage()));
    }

    @ExceptionHandler(RegistroPossuiVinculoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity tratarErroDeExclusaoDeEntidade(RegistroPossuiVinculoException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new DadosDetalhamentoErro(ex.getMessage()));
    }
}
