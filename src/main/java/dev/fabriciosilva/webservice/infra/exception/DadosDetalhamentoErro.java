package dev.fabriciosilva.webservice.infra.exception;

import java.time.LocalDateTime;

public class DadosDetalhamentoErro {

    private String mensagem;
    private LocalDateTime timestamp;

    public DadosDetalhamentoErro(String mensagem) {
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
