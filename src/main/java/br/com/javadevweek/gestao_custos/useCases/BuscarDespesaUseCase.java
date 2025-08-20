package br.com.javadevweek.gestao_custos.useCases;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.repository.DespesaRespository;

@Service
public class BuscarDespesaUseCase {

    @Autowired
    private DespesaRespository despesaRespository;
    
    public List<Despesa> execute(String email, LocalDate data){
       List<Despesa> despesas;
        
        if (data != null) {
            despesas = despesaRespository.findByEmailAndData(email, data);
        } else {
            despesas = despesaRespository.findByEmail(email);
        }
        
        return despesas;
    }
}
