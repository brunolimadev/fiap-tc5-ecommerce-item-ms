package br.com.fiap.ecommerce_item_ms;

import java.util.List;

public interface ItemManagementOutputPort {

  ItemEntity createItem(ItemEntity itemEntity) throws OutputPortException;

  List<ItemEntity> getItems() throws OutputPortException;

  ItemEntity getItem(Long id) throws OutputPortException;

  ItemEntity removeItem(Long id) throws OutputPortException;

  ItemEntity updateItem(Long id, ItemEntity itemEntity) throws OutputPortException;
  
}