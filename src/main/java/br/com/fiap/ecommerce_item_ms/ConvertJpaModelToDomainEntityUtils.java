package br.com.fiap.ecommerce_item_ms;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertJpaModelToDomainEntityUtils {

  public static ItemEntity convert(ItemModel itemModel) {



    return ItemEntity
            .builder()
            .id(itemModel.getId())
            .description(itemModel.getDescription())
            .price(itemModel.getPrice())
            .storeQuantity(itemModel.getStoreQuantity())
            .createDateTime(itemModel.getCreateDateTime())
            .updateDateTime(itemModel.getUpdateDateTime())
            .build();

  }

}