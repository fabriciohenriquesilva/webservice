package dev.fabriciosilva.webservice.domain.notafiscal.validations;

import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalForm;

public interface ValidadorNotaFiscal {

    void validar(NotaFiscalForm form);
}
