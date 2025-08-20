package br.com.javadevweek.gestao_custos.performance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.repository.DespesaRespository;

//@Component
public class GestaoDeDespesaSeeder implements CommandLineRunner {

    @Autowired
    DespesaRespository despesaRespository;

    @Override
    public void run(String... args) throws Exception {
        List<Despesa> despesas = new ArrayList<>();
        
        for (int i = 0; i <= 150000; i++) {
            Despesa despesa = new Despesa();
            despesa.setDescricao("Despesa " + i);
            despesa.setData(LocalDate.now().minusDays((i % 30)));
            despesa.setValor(BigDecimal.valueOf((i % 50) * 10));
            despesa.setCategoria("Categoria " + (i % 5));
            despesa.setEmail("usuario" + (i % 100) + "@example.com");

            despesas.add(despesa);
        }
        despesaRespository.saveAll(despesas);
        System.out.println("Seeded 150000 despesas.");
    }

}
