package dev.fabriciosilva.webservice.infra.exception;

public class DadosValidacaoException extends RuntimeException {

    public DadosValidacaoException(String mensagem) {
        super(mensagem);
    }
}
