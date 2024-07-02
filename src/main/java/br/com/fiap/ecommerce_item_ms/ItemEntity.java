package br.com.fiap.ecommerce_item_ms;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

  private Long id;
  private String description;
  private String price;
  private String storeQuantity;
  private LocalDateTime createDateTime;
  private LocalDateTime updateDateTime;

}