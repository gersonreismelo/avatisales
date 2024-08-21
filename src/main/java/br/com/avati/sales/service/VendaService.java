package br.com.avati.sales.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.avati.sales.model.Item;
import br.com.avati.sales.repository.ItemRepository;

@Service
public class VendaService {

    @Autowired
    private ItemRepository itemRepository;

    public BigDecimal calcularTotalVenda(Long itemId, Integer quantidade) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        if (item.getPreco() == null || quantidade == null) {
            throw new IllegalArgumentException("Preço ou quantidade não podem ser nulos");
        }
        return item.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }
}
