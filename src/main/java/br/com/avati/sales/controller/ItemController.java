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

import br.com.avati.sales.model.Item;
import br.com.avati.sales.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("itens")
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> index() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        log.info("Buscar item por id: {}", id);

        return itemRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        log.info("Cadastrando item: {}", item);

        return itemRepository.save(item);
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody Item item) {
        log.info("Atualizando item id {} para {}", id, item);

        verificarSeExisteItem(id);

        item.setId(id);
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        log.info("Apagando item {}", id);

        verificarSeExisteItem(id);
        itemRepository.deleteById(id);
    }

    private void verificarSeExisteItem(Long id) {
        itemRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }
}
