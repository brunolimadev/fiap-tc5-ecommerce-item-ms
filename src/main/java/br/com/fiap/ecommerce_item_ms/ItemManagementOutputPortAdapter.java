package br.com.fiap.ecommerce_item_ms;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiap.ecommerce_item_ms.MessageEnumUtils.*;

public class ItemManagementOutputPortAdapter implements ItemManagementOutputPort {

  private final ItemRepository itemRepository;

  public ItemManagementOutputPortAdapter(ItemRepository itemRepository) {

    this.itemRepository = itemRepository;

  }

  @Override
  public ItemEntity createItem(ItemEntity itemEntity) throws OutputPortException {

    try {

      return ConvertJpaModelToDomainEntityUtils.convert(
              itemRepository.save(ConvertDomainEntityToJpaModelUtils.convert(itemEntity))
      );

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_CREATE_ITEMS_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public List<ItemEntity> getItems() throws OutputPortException {

    try {

      return itemRepository.findAll()
              .stream()
              .map(ConvertJpaModelToDomainEntityUtils::convert)
              .toList();

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_GET_ITEMS_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public ItemEntity getItem(Long id) throws OutputPortException {

    try {

      return ConvertJpaModelToDomainEntityUtils.convert(itemRepository.findById(id).orElseThrow());

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_GET_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public ItemEntity removeItem(Long id) throws OutputPortException {

    try {

      var product = itemRepository.findById(id).orElseThrow();

      itemRepository.deleteById(product.getId());

      return ConvertJpaModelToDomainEntityUtils.convert(product);

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_REMOVE_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public ItemEntity updateItem(Long id, ItemEntity itemEntity) throws OutputPortException {

    try {

      var product = itemRepository.findById(id).orElseThrow();

      product.setDescription(itemEntity.getDescription());
      product.setPrice(itemEntity.getPrice());
      product.setStoreQuantity(itemEntity.getStoreQuantity());
      product.setUpdateDateTime(LocalDateTime.now());

      return ConvertJpaModelToDomainEntityUtils.convert(itemRepository.save(product));

    } catch (Exception exception) {

      throw new OutputPortException(ITEM_MANAGEMENT_UPDATE_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

}