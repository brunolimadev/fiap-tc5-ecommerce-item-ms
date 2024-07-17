package br.com.fiap.ecommerce_item_ms.adapter.config;

import br.com.fiap.ecommerce_item_ms.adapter.ports.outputport.SessionManagementOutputPortAdapter;
import br.com.fiap.ecommerce_item_ms.ports.outputport.ItemManagementOutputPort;
import br.com.fiap.ecommerce_item_ms.adapter.ports.outputport.ItemManagementOutputPortAdapter;
import br.com.fiap.ecommerce_item_ms.adapter.repositories.ItemRepository;
import br.com.fiap.ecommerce_item_ms.ports.outputport.SessionManagementOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortsConfig {

  @Bean
  public SessionManagementOutputPort sessionManagementOutputPort() {

    return new SessionManagementOutputPortAdapter();

  }

  @Bean
  public ItemManagementOutputPort itemManagementOutputPort(
          ItemRepository itemRepository,
          SessionManagementOutputPort sessionManagementOutputPort) {

    return new ItemManagementOutputPortAdapter(itemRepository, sessionManagementOutputPort);

  }

}
