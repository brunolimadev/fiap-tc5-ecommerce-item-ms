package br.com.fiap.ecommerce_item_ms.ports.outputport;

public interface SessionManagementOutputPort {

  Object getSession(String sessionId);

}