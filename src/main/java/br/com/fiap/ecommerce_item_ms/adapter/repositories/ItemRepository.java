package br.com.fiap.ecommerce_item_ms.adapter.repositories;

import br.com.fiap.ecommerce_item_ms.adapter.repositories.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {}