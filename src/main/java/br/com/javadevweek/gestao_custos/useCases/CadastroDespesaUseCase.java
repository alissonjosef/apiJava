package br.com.javadevweek.gestao_custos.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.repository.DespesaRespository;

@Service
public class CadastroDespesaUseCase {

    @Autowired
    private DespesaRespository despesaRespository;

    public Despesa execute(Despesa despesa) {

        if (despesa.getDescricao() == null || despesa.getData() == null
                || despesa.getValor() == null || despesa.getCategoria() == null || despesa.getEmail() == null) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        }

        despesa = despesaRespository.save(despesa);
        return despesa;
    }
}
