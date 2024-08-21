package br.com.avati.sales.controller;

import java.math.BigDecimal;
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

import br.com.avati.sales.model.Item;
import br.com.avati.sales.model.Pessoa;
import br.com.avati.sales.model.Venda;
import br.com.avati.sales.repository.ItemRepository;
import br.com.avati.sales.repository.PessoaRepository;
import br.com.avati.sales.repository.VendaRepository;
import br.com.avati.sales.service.VendaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("vendas")
public class VendaController {
    
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> index() {
        return vendaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> get(@PathVariable Long id) {
        log.info("Buscar venda por id: {}", id);

        return vendaRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> create(@RequestBody Venda venda) {

        // Verificar se a pessoa existe
        Pessoa pessoa = pessoaRepository.findById(venda.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        // Verificar se o item existe
        Item item = itemRepository.findById(venda.getItem().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        // Associar as entidades à venda
        venda.setPessoa(pessoa);
        venda.setItem(item);

        // Calcular o valor total da venda
        BigDecimal total = vendaService.calcularTotalVenda(item.getId(), venda.getQuantidade());
        venda.setTotal(total);

        // Salvar a venda
        Venda novaVenda = vendaRepository.save(venda);

        log.info("Cadastrando venda: {}", venda);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody Venda venda) {
        log.info("Atualizando venda id {} para {}", id, venda);

        // Verificar se a venda existe
        verificarSeExisteVenda(id);

        // Verificar se a pessoa existe
        Pessoa pessoa = pessoaRepository.findById(venda.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        // Verificar se o item existe
        Item item = itemRepository.findById(venda.getItem().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        // Atualizar as entidades associadas
        venda.setPessoa(pessoa);
        venda.setItem(item);

        // Recalcular o valor total da venda
        BigDecimal total = vendaService.calcularTotalVenda(item.getId(), venda.getQuantidade());
        venda.setTotal(total);

        // Atualizar a venda
        venda.setId(id);
        Venda vendaAtualizada = vendaRepository.save(venda);

        log.info("Cadastrando venda: {}", venda);

        return ResponseEntity.ok(vendaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        log.info("Apagando venda {}", id);

        verificarSeExisteVenda(id);
        vendaRepository.deleteById(id);
    }

    private void verificarSeExisteVenda(Long id) {
        vendaRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
    }
}
