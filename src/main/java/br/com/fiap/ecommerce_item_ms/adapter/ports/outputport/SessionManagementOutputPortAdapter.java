package br.com.fiap.ecommerce_item_ms.adapter.ports.outputport;

import br.com.fiap.ecommerce_item_ms.ports.outputport.SessionManagementOutputPort;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class SessionManagementOutputPortAdapter implements SessionManagementOutputPort {

  private final WebClient client;

  public SessionManagementOutputPortAdapter(@Value("${application.session.host-url}") String sessionServiceHost) {
    this.client = WebClient.create(sessionServiceHost);
  }

  @Override
  public Object getSession(String sessionId) {

    Mono<Object> response = client.get()
        .uri("/ecommerce-management/api/v1/sessions/{id}", sessionId)
        .retrieve()
        .bodyToMono(Object.class);

    return response.block();

  }

}