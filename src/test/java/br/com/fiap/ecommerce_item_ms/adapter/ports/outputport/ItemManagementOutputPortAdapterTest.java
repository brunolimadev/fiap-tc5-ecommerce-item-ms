package br.com.fiap.ecommerce_item_ms.adapter.ports.outputport;

import br.com.fiap.ecommerce_item_ms.adapter.repositories.ItemRepository;
import br.com.fiap.ecommerce_item_ms.adapter.repositories.model.ItemModel;
import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;
import br.com.fiap.ecommerce_item_ms.domain.exception.EntityException;
import br.com.fiap.ecommerce_item_ms.mock.ItemEntityMock;
import br.com.fiap.ecommerce_item_ms.mock.ItemModelMock;
import br.com.fiap.ecommerce_item_ms.ports.exception.OutputPortException;
import br.com.fiap.ecommerce_item_ms.ports.outputport.ItemManagementOutputPort;
import br.com.fiap.ecommerce_item_ms.ports.outputport.SessionManagementOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static br.com.fiap.ecommerce_item_ms.utils.MessageEnumUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class ItemManagementOutputPortAdapterTest {

  @Mock
  private ItemRepository itemRepository;

  @Mock
  private SessionManagementOutputPort sessionManagementOutputPort;

  private ItemManagementOutputPort itemManagementOutputPort;
  private AutoCloseable openMocks;

  @BeforeEach
  void setup() {

    openMocks = MockitoAnnotations.openMocks(this);
    itemManagementOutputPort = new ItemManagementOutputPortAdapter(itemRepository, sessionManagementOutputPort);

  }

  @AfterEach
  void tearDown() throws Exception {

    openMocks.close();

  }

  @Test
  void shouldCreateItemWithSuccess() {

    //Arrange
    var itemModel = ItemModelMock.get();
    var itemEntityId = 1L;
    when(itemRepository.save(any(ItemModel.class))).thenReturn(itemModel);

    //Act
    var response = itemManagementOutputPort.createItem(ItemEntityMock.get(), "");

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(ItemEntity.class);

    assertThat(response.getId()).isEqualTo(itemEntityId);

  }

  @Test
  void shouldThrowEntityExceptionTryingCreateItem() {

    //Arrange
    var itemEntity = ItemEntityMock.get();
    when(itemRepository.save(any(ItemModel.class))).thenThrow(EntityException.class);

    //Act & Assert
    assertThatThrownBy(() -> itemManagementOutputPort.createItem(itemEntity, ""))
            .isInstanceOf(EntityException.class);

  }

  @Test
  void shouldThrowOutputPortExceptionTryingCreateItem() {

    //Arrange
    var itemEntity = ItemEntityMock.get();
    when(itemRepository.save(any(ItemModel.class))).thenThrow(OutputPortException.class);

    //Act & Assert
    assertThatThrownBy(() -> itemManagementOutputPort.createItem(itemEntity, ""))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(ITEM_MANAGEMENT_CREATE_ITEMS_OUTPUT_PORT_EXCEPTION.getMessage());

  }

  @Test
  void shouldGetItemsWithSuccess() {

    //Arrange
    var itemModelList = ItemModelMock.getList();
    when(itemRepository.findAll()).thenReturn(itemModelList);

    //Act
    var response = itemManagementOutputPort.getItems("");

    //Assert
    assertThat(response)
            .isNotNull()
            .isNotEmpty()
            .asList()
            .hasSize(itemModelList.size());

  }

  @Test
  void shouldThrowOutputPortExceptionTryingGetItems() {

    //Arrange
    when(itemRepository.findAll()).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> itemManagementOutputPort.getItems(""))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(ITEM_MANAGEMENT_GET_ITEMS_OUTPUT_PORT_EXCEPTION.getMessage());
    verify(itemRepository, times(1)).findAll();

  }

  @Test
  void shouldGetItemWithSuccess() {

    //Arrange
    var itemModel = ItemModelMock.get();
    var itemEntity = ItemEntityMock.get();
    var itemEntityId = itemEntity.getId();
    when(itemRepository.findById(anyLong())).thenReturn(Optional.of(itemModel));

    //Act
    var response = itemManagementOutputPort.getItem(itemEntityId, "");

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(ItemEntity.class);

    assertThat(response.getId()).isEqualTo(itemEntityId);
    verify(itemRepository, times(1)).findById(anyLong());

  }

  @Test
  void shouldThrowOutputPortExceptionTryingGetItem() {

    //Arrange
    var itemEntity = ItemEntityMock.get();
    var itemEntityId = itemEntity.getId();
    when(itemRepository.findById(anyLong())).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> itemManagementOutputPort.getItem(itemEntityId, ""))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(ITEM_MANAGEMENT_GET_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());
    verify(itemRepository, times(1)).findById(anyLong());

  }

  @Test
  void shouldRemoveItemWithSuccess() {

    //Arrange
    var itemModel = ItemModelMock.get();
    var itemEntity = ItemEntityMock.get();
    var itemEntityId = itemEntity.getId();
    when(itemRepository.findById(anyLong())).thenReturn(Optional.of(itemModel));
    doNothing().when(itemRepository).deleteById(anyLong());

    //Act
    var response = itemManagementOutputPort.removeItem(itemEntityId, "");

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(ItemEntity.class);

    assertThat(response.getId()).isEqualTo(itemEntityId);

  }

  @Test
  void shouldThrowOutputPortExceptionTryingRemoveItem() {

    //Arrange
    var itemModel = ItemModelMock.get();
    var itemEntity = ItemEntityMock.get();
    var itemEntityId = itemEntity.getId();
    when(itemRepository.findById(anyLong())).thenReturn(Optional.of(itemModel));
    doThrow(RuntimeException.class).when(itemRepository).deleteById(anyLong());

    //Act & Assert
    assertThatThrownBy(() -> itemManagementOutputPort.removeItem(itemEntityId, ""))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(ITEM_MANAGEMENT_REMOVE_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());

  }

  @Test
  void shouldUpdateItemWithSuccess() {

    //Arrange
    var itemModel = ItemModelMock.get();
    var itemEntity = ItemEntityMock.get();
    var itemEntityId = itemEntity.getId();
    when(itemRepository.findById(anyLong())).thenReturn(Optional.of(itemModel));
    when(itemRepository.save(any(ItemModel.class))).thenReturn(itemModel);

    //Act
    var response = itemManagementOutputPort.updateItem(itemEntityId, ItemEntityMock.get(), "");

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(ItemEntity.class);

    assertThat(response.getId()).isEqualTo(itemEntityId);

  }

  @Test
  void shouldThrowOutputPortExceptionTryingUpdateItem() {

    //Arrange
    var itemModel = ItemModelMock.get();
    var itemEntity = ItemEntityMock.get();
    var itemEntityId = itemEntity.getId();
    when(itemRepository.findById(anyLong())).thenReturn(Optional.of(itemModel));
    when(itemRepository.save(any(ItemModel.class))).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> itemManagementOutputPort.updateItem(itemEntityId, itemEntity, ""))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(ITEM_MANAGEMENT_UPDATE_ITEM_OUTPUT_PORT_EXCEPTION.getMessage());

  }


}