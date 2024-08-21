package br.com.avati.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.avati.sales.model.Pessoa;
import br.com.avati.sales.repository.PessoaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> index() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);

        return pessoaRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoa create(@RequestBody Pessoa pessoa) {
        log.info("Cadastrando pessoa: {}", pessoa);

        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        log.info("Atualizando pessoa id {} para {}", id, pessoa);

        verificarSeExistePessoa(id);

        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        log.info("Apagando pessoa {}", id);

        verificarSeExistePessoa(id);
        pessoaRepository.deleteById(id);
    }

    private void verificarSeExistePessoa(Long id) {
        pessoaRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
    }

}
