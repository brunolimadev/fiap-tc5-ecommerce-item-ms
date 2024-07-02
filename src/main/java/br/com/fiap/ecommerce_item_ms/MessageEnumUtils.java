package br.com.fiap.ecommerce_item_ms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MessageEnumUtils {

  ITEM_MANAGEMENT_CREATE_ITEMS_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar criar o item"),
  ITEM_MANAGEMENT_GET_ITEMS_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar recuperar os items"),
  ITEM_MANAGEMENT_GET_ITEM_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar recuperar o item"),
  ITEM_MANAGEMENT_REMOVE_ITEM_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar remover o item"),
  ITEM_MANAGEMENT_UPDATE_ITEM_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar atualizar o item");

  public static final String TITLE_PROCESS_ITEM = "Solicitação recebida";
  public static final String TIME_DOMAIN_EXCEPTION = "Solicitação recebida";
  public static final String TITLE_PORTS_EXCEPTION =  "Erro de processamento";
  private String message;

}