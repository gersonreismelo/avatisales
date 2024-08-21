package br.com.avati.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avati.sales.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {}
