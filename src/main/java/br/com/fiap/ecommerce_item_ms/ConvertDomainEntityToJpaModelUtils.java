package br.com.fiap.ecommerce_item_ms;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class ConvertDomainEntityToJpaModelUtils {

  public static ItemModel convert(ItemEntity itemEntity) {

    return ItemModel
            .builder()
            .id(itemEntity.getId())
            .description(itemEntity.getDescription())
            .price(itemEntity.getPrice())
            .storeQuantity(itemEntity.getStoreQuantity())
            .createDateTime(LocalDateTime.now())
            .build();

  }

}
