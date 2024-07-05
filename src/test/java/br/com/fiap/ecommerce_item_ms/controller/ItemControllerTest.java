package br.com.fiap.ecommerce_item_ms.controller;

import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;
import br.com.fiap.ecommerce_item_ms.mock.ItemEntityMock;
import br.com.fiap.ecommerce_item_ms.ports.outputport.ItemManagementOutputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = ItemController.class)
@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ItemManagementOutputPort itemManagementOutputPort;

  private ItemEntity itemEntity;

  @BeforeEach
  public void init () {

    itemEntity = ItemEntityMock.get();

  }

  @Test
  void shouldCreateItemWithSuccess() throws Exception {

    //Arrange
    given(itemManagementOutputPort.createItem(any())).willAnswer(invocation -> invocation.getArgument(0));

    //Act
    var response = mockMvc.perform(post("http://localhost:8080/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(itemEntity)));

    //Assert
    response
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(jsonPath("$.description", CoreMatchers.is(itemEntity.getDescription())));

  }

  @Test
  void shouldGetItemsWithSuccess() throws Exception {

    //Arrange
    var items = List.of(itemEntity);
    when(itemManagementOutputPort.getItems()).thenReturn(items);

    //Act
    var response = mockMvc.perform(get("http://localhost:8080/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(itemEntity)));

    //Assert
    response
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$", hasSize(1)));

  }

  @Test
  void shouldGetItemWithSuccess() throws Exception {

    //Arrange
    var itemId = itemEntity.getId();
    when(itemManagementOutputPort.getItem(itemId)).thenReturn(itemEntity);

    //Act
    var response = mockMvc.perform(get("http://localhost:8080/items/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(itemEntity)));

    //Assert
    response
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.description", CoreMatchers.is(itemEntity.getDescription())));

  }

  @Test
  void shouldRemoveItemWithSuccess() throws Exception {

    //Arrange
    var itemId = itemEntity.getId();
    when(itemManagementOutputPort.removeItem(itemId)).thenReturn(itemEntity);

    //Act
    var response = mockMvc.perform(delete("http://localhost:8080/items/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(itemEntity)));

    //Assert
    response
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.description", CoreMatchers.is(itemEntity.getDescription())));

  }

  @Test
  void shouldUpdateItemWithSuccess() throws Exception {

    //Arrange
    var itemId = itemEntity.getId();
    when(itemManagementOutputPort.updateItem(itemId, itemEntity)).thenReturn(itemEntity);

    //Act
    var response = mockMvc.perform(put("http://localhost:8080/items/1")
            .param("item_id", String.valueOf(itemId))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(itemEntity)));

    System.out.println(response.toString());

    //Assert
    response
            .andExpect(MockMvcResultMatchers.status().isOk());

  }

}