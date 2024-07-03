package br.com.fiap.ecommerce_item_ms.adapter.config;

import br.com.fiap.ecommerce_item_ms.ports.outputport.ItemManagementOutputPort;
import br.com.fiap.ecommerce_item_ms.adapter.ports.outputport.ItemManagementOutputPortAdapter;
import br.com.fiap.ecommerce_item_ms.adapter.repositories.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortsConfig {

  @Bean
  public ItemManagementOutputPort itemManagementOutputPort(ItemRepository itemRepository) {

    return new ItemManagementOutputPortAdapter(itemRepository);

  }

}
