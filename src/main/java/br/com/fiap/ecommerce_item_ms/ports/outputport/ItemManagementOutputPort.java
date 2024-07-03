package br.com.fiap.ecommerce_item_ms.ports.outputport;

import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;
import br.com.fiap.ecommerce_item_ms.ports.exception.OutputPortException;

import java.util.List;

public interface ItemManagementOutputPort {

  ItemEntity createItem(ItemEntity itemEntity) throws OutputPortException;

  List<ItemEntity> getItems() throws OutputPortException;

  ItemEntity getItem(Long id) throws OutputPortException;

  ItemEntity removeItem(Long id) throws OutputPortException;

  ItemEntity updateItem(Long id, ItemEntity itemEntity) throws OutputPortException;
  
}