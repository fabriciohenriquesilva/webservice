package dev.fabriciosilva.webservice.domain.notafiscal.validations;

import dev.fabriciosilva.webservice.domain.notafiscal.dto.NotaFiscalForm;
import dev.fabriciosilva.webservice.domain.produto.ProdutoRepository;
import dev.fabriciosilva.webservice.infra.exception.DadosValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorProdutoExistente implements ValidadorNotaFiscal {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void validar(NotaFiscalForm form) {
        form.getItens().forEach(itemForm -> {
            Long produtoId = itemForm.getProduto().getId();

            if (!produtoRepository.existsById(produtoId)) {
                throw new DadosValidacaoException("O produto com o ID informado não está cadastrado no sistema! ID = " + produtoId);
            }
        });
    }
}
