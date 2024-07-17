package br.com.fiap.ecommerce_item_ms.ports.outputport;

import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;
import br.com.fiap.ecommerce_item_ms.ports.exception.OutputPortException;

import java.util.List;

public interface ItemManagementOutputPort {

  ItemEntity createItem(ItemEntity itemEntity, String sessionId) throws OutputPortException;

  List<ItemEntity> getItems(String sessionId) throws OutputPortException;

  ItemEntity getItem(Long id, String sessionId) throws OutputPortException;

  ItemEntity removeItem(Long id, String sessionId) throws OutputPortException;

  ItemEntity updateItem(Long id, ItemEntity itemEntity, String sessionId) throws OutputPortException;
  
}