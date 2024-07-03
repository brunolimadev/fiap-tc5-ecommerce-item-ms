package br.com.fiap.ecommerce_item_ms.utils;

import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;
import br.com.fiap.ecommerce_item_ms.mock.ItemEntityMock;
import br.com.fiap.ecommerce_item_ms.mock.ItemModelMock;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConvertDomainEntityToJpaModelUtilsTest {

  @Test
  void shouldCreateItemEntityGivenAItemModel() {

    //Arrange
    var itemModel = ItemModelMock.get();
    var expectItemEntity = ItemEntityMock.get();

    //Act
    var itemEntityConverted = ConvertJpaModelToDomainEntityUtils.convert(itemModel);

    //Assert
    assertThat(itemEntityConverted)
            .isInstanceOf(ItemEntity.class);

    assertThat(itemEntityConverted.getId()).isEqualTo(expectItemEntity.getId());
    assertThat(itemEntityConverted.getDescription()).isEqualTo(expectItemEntity.getDescription());
    assertThat(itemEntityConverted.getStoreQuantity()).isEqualTo(expectItemEntity.getStoreQuantity());
    assertThat(itemEntityConverted.getCreateDateTime()).isEqualTo(expectItemEntity.getCreateDateTime());
    assertThat(itemEntityConverted.getUpdateDateTime()).isEqualTo(expectItemEntity.getUpdateDateTime());

  }

}