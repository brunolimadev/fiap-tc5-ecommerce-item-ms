package br.com.fiap.ecommerce_item_ms.mock;

import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;

import java.util.Collections;
import java.util.List;

public class ItemEntityMock {

  public static ItemEntity get() {

    return ItemEntity
            .builder()
            .id(1L)
            .description("Camiseta da Adidas GG")
            .price(20.90)
            .storeQuantity(2)
            .build();

  }

  public static List<ItemEntity> getList() {

    return Collections.singletonList(get());

  }

}