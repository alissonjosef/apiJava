package br.com.javadevweek.gestao_custos.performance;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.repository.DespesaRespository;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/gestao/performace")
@RestController
@EnableCaching
public class GestaoDespesaPerformace {
    
    @Autowired
    DespesaRespository respository;

    @GetMapping("/listar")
    public ResponseEntity<Page<Despesa>> listarComPaginacao(Pageable pageable) {
        return ResponseEntity.ok(respository.findAll(pageable));
    }

    @GetMapping("/listar/{email}")
    public ResponseEntity<Page<Despesa>> listarPorEmail(@PathVariable String email, Pageable pageable) {
        return ResponseEntity.ok(respository.findByEmail(email, pageable));
    }

    @Cacheable(value = "despesasPorEmail", key = "#email + '_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    @GetMapping("/cache/{email}")
    public ResponseEntity<Page<Despesa>> cache(@PathVariable String email, Pageable pageable) {
        return ResponseEntity.ok(respository.findByEmail(email, pageable));
    }

}
