package br.com.fiap.ecommerce_item_ms.controller;

import br.com.fiap.ecommerce_item_ms.domain.entities.ItemEntity;
import br.com.fiap.ecommerce_item_ms.ports.exception.OutputPortException;
import br.com.fiap.ecommerce_item_ms.ports.outputport.ItemManagementOutputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
@Tag(name = "Item Controller", description = "Customer can manage items through API resources")
public class ItemController {

  private final ItemManagementOutputPort itemManagementOutputPort;

  public ItemController(
          ItemManagementOutputPort itemManagementOutputPort
  ) {
    
    this.itemManagementOutputPort = itemManagementOutputPort;

  }

  @Operation(summary = "Create item")
  @ApiResponse(
          responseCode = "201",
          description = "Returns a created item"
  )
  @PostMapping
  public ResponseEntity<ItemEntity> createItem(
          @RequestBody ItemEntity itemEntity
  ) throws OutputPortException {

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(itemManagementOutputPort.createItem(itemEntity));

  }

  @Operation(summary = "List all items")
  @ApiResponse(responseCode = "200", description = "Gets list of all items")
  @GetMapping
  public ResponseEntity<List<ItemEntity>> getAllItems() throws OutputPortException {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(itemManagementOutputPort.getItems());

  }

  @Operation(summary = "Returns a item by id")
  @ApiResponse(responseCode = "200", description = "Gets a specific item")
  @GetMapping(value = "{item_id}")
  public ResponseEntity<ItemEntity> getItem(@PathVariable("item_id") Long id) throws OutputPortException {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(itemManagementOutputPort.getItem(id));

  }

  @Operation(summary = "Remove a item by id")
  @ApiResponse(responseCode = "200", description = "Returns a removed item")
  @DeleteMapping(value = "{item_id}")
  public ResponseEntity<ItemEntity> removeItem(@PathVariable("item_id") Long id) throws OutputPortException {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(itemManagementOutputPort.removeItem(id));

  }

  @Operation(summary = "Update a item by id")
  @ApiResponse(responseCode = "200", description = "Returns a updated item")
  @PutMapping(value = "/{item_id}")
  public ResponseEntity<ItemEntity> updateItem(
          @PathVariable("item_id") Long id,
          @RequestBody ItemEntity itemEntity
  ) throws OutputPortException {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(itemManagementOutputPort.updateItem(id, itemEntity));

  }

}