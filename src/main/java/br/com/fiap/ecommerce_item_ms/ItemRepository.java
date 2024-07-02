package br.com.fiap.ecommerce_item_ms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {}