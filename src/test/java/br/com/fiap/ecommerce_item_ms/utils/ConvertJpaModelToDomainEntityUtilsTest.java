package br.com.fiap.ecommerce_item_ms.utils;

import br.com.fiap.ecommerce_item_ms.adapter.repositories.model.ItemModel;
import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;
import br.com.fiap.ecommerce_item_ms.mock.ItemEntityMock;
import br.com.fiap.ecommerce_item_ms.mock.ItemModelMock;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConvertJpaModelToDomainEntityUtilsTest {

  @Test
  void shouldCreateItemModelGivenAItemEntity() {

    //Arrange
    var itemEntity = ItemEntityMock.get();
    var expectItemModel = ItemModelMock.get();

    //Act
    var itemModelConverted = ConvertDomainEntityToJpaModelUtils.convert(itemEntity);

    //Assert
    assertThat(itemModelConverted)
            .isInstanceOf(ItemModel.class);

    assertThat(itemModelConverted.getId()).isEqualTo(expectItemModel.getId());
    assertThat(itemModelConverted.getDescription()).isEqualTo(expectItemModel.getDescription());
    assertThat(itemModelConverted.getStoreQuantity()).isEqualTo(expectItemModel.getStoreQuantity());

  }

}