package br.com.fiap.ecommerce_item_ms.adapter.ports.outputport;

import br.com.fiap.ecommerce_item_ms.adapter.repositories.ItemRepository;
import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;
import br.com.fiap.ecommerce_item_ms.domain.exception.EntityException;
import br.com.fiap.ecommerce_item_ms.ports.outputport.ItemManagementOutputPort;
import br.com.fiap.ecommerce_item_ms.ports.exception.OutputPortException;
import br.com.fiap.ecommerce_item_ms.ports.outputport.SessionManagementOutputPort;
import br.com.fiap.ecommerce_item_ms.utils.ConvertDomainEntityToJpaModelUtils;
import br.com.fiap.ecommerce_item_ms.utils.ConvertJpaModelToDomainEntityUtils;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiap.ecommerce_item_ms.utils.MessageEnumUtils.*;

public class ItemManagementOutputPortAdapter implements ItemManagementOutputPort {

  private final ItemRepository itemRepository;
  private final SessionManagementOutputPort sessionManagementOutputPort;

  public ItemManagementOutputPortAdapter(
          ItemRepository itemRepository,
          SessionManagementOutputPort sessionManagementOutputPort) {

    this.itemRepository = itemRepository;
    this.sessionManagementOutputPort = sessionManagementOutputPort;

  }

  @Override
  public ItemEntity createItem(ItemEntity itemEntity, String sessionId) throws OutputPortException {

    try {

      validateActiveSession(sessionManagementOutputPort.getSession(sessionId));

      var item = ConvertDomainEntityToJpaModelUtils.convert(itemEntity);

      item.setId(null);

      return ConvertJpaModelToDomainEntityUtils.convert(
              itemRepository.save(item)
      );

    } catch (EntityException entityException) {

      throw entityException;

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_CREATE_ITEMS_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public List<ItemEntity> getItems(String sessionId) throws OutputPortException {

    try {

      validateActiveSession(sessionManagementOutputPort.getSession(sessionId));

      return itemRepository.findAll()
              .stream()
              .map(ConvertJpaModelToDomainEntityUtils::convert)
              .toList();

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_GET_ITEMS_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public ItemEntity getItem(Long id, String sessionId) throws OutputPortException {

    try {

      validateActiveSession(sessionManagementOutputPort.getSession(sessionId));

      return ConvertJpaModelToDomainEntityUtils.convert(itemRepository.findById(id).orElseThrow());

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_GET_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public ItemEntity removeItem(Long id, String sessionId) throws OutputPortException {

    try {

      validateActiveSession(sessionManagementOutputPort.getSession(sessionId));

      var item = itemRepository.findById(id).orElseThrow();

      itemRepository.deleteById(item.getId());

      return ConvertJpaModelToDomainEntityUtils.convert(item);

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_REMOVE_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public ItemEntity updateItem(Long id, ItemEntity itemEntity, String sessionId) throws OutputPortException {

    try {

      validateActiveSession(sessionManagementOutputPort.getSession(sessionId));

      var item = itemRepository.findById(id).orElseThrow();

      item.setDescription(itemEntity.getDescription());
      item.setPrice(itemEntity.getPrice());
      item.setStoreQuantity(itemEntity.getStoreQuantity());
      item.setUpdateDateTime(LocalDateTime.now());

      return ConvertJpaModelToDomainEntityUtils.convert(itemRepository.save(item));

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_UPDATE_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  private void validateActiveSession(Object object) {

    if (object == null) {

      throw new OutputPortException("Usuario com sessao inativa");

    }

  }

}