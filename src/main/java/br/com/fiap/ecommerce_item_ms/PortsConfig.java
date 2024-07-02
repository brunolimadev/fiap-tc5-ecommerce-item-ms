package br.com.fiap.ecommerce_item_ms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortsConfig {

  @Bean
  public ItemManagementOutputPort itemManagementOutputPort(ItemRepository itemRepository) {

    return new ItemManagementOutputPortAdapter(itemRepository);

  }

}
