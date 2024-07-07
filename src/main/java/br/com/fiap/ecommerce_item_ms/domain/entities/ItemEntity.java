package br.com.fiap.ecommerce_item_ms.domain.entities;

import br.com.fiap.ecommerce_item_ms.domain.exception.EntityException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.time.LocalDateTime;

import static br.com.fiap.ecommerce_item_ms.utils.MessageEnumUtils.ENTITY_EXCEPTION;

@Getter
@Builder
public class ItemEntity {

  @Hidden
  private Long id;
  private String description;
  private Double price;
  private Integer storeQuantity;
  @Hidden
  private LocalDateTime createDateTime;
  @Hidden
  private LocalDateTime updateDateTime;

  public ItemEntity(
          Long id, String description, Double price, Integer storeQuantity,
          LocalDateTime createDateTime, LocalDateTime updateDateTime
  ) {

    validateValues(description, price, storeQuantity);

    this.id = id;
    this.description = description;
    this.price = price;
    this.storeQuantity = storeQuantity;
    this.createDateTime = createDateTime;
    this.updateDateTime = updateDateTime;

  }

  private void validateValues (
          String description, Double price, Integer storeQuantity
  ) throws EntityException {

    if (
            description == null || price == null || storeQuantity == null
    ) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

    if (
            description.isEmpty() || price <= 0 || storeQuantity <= 0
    ) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

  }


}