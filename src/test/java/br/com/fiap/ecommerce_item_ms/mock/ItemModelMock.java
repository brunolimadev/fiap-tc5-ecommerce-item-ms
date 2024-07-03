package br.com.fiap.ecommerce_item_ms.mock;

import br.com.fiap.ecommerce_item_ms.adapter.repositories.model.ItemModel;

import java.util.Collections;
import java.util.List;

public class ItemModelMock {

  public static ItemModel get() {

    return ItemModel
            .builder()
            .id(1L)
            .description("Camiseta da Adidas GG")
            .price(20.90)
            .storeQuantity(2)
            .build();

  }

  public static List<ItemModel> getList() {

    return Collections.singletonList(get());

  }

}