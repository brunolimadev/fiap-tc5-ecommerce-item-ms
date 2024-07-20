package br.com.fiap.ecommerce_item_ms.adapter.config;

import br.com.fiap.ecommerce_item_ms.adapter.ports.outputport.SessionManagementOutputPortAdapter;
import br.com.fiap.ecommerce_item_ms.ports.outputport.ItemManagementOutputPort;
import br.com.fiap.ecommerce_item_ms.adapter.ports.outputport.ItemManagementOutputPortAdapter;
import br.com.fiap.ecommerce_item_ms.adapter.repositories.ItemRepository;
import br.com.fiap.ecommerce_item_ms.ports.outputport.SessionManagementOutputPort;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortsConfig {

  @Value("${application.session.host-url}")
  private String sessionServiceHost;

  @Bean
  public SessionManagementOutputPort sessionManagementOutputPort() {

    return new SessionManagementOutputPortAdapter(sessionServiceHost);

  }

  @Bean
  public ItemManagementOutputPort itemManagementOutputPort(
      ItemRepository itemRepository,
      SessionManagementOutputPort sessionManagementOutputPort) {

    return new ItemManagementOutputPortAdapter(itemRepository, sessionManagementOutputPort);

  }

}
