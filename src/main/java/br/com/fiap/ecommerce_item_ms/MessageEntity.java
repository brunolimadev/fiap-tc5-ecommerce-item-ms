package br.com.fiap.ecommerce_item_ms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessageEntity {

  private String title;
  private String message;

}