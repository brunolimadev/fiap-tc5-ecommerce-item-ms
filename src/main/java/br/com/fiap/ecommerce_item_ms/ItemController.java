package br.com.fiap.ecommerce_item_ms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {

  private final ItemManagementOutputPort itemManagementOutputPort;

  public ItemController(
          ItemManagementOutputPort itemManagementOutputPort
  ) {
    
    this.itemManagementOutputPort = itemManagementOutputPort;

  }

  @PostMapping
  public ResponseEntity<ItemEntity> processProducts(
          @RequestBody ItemEntity itemEntity
  ) throws UseCaseException {

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(itemManagementOutputPort.createItem(itemEntity));

  }

  @GetMapping
  public ResponseEntity<List<ItemEntity>> getAllProducts() {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(itemManagementOutputPort.getItems());

  }

  @GetMapping(value = "{item_id}")
  public ResponseEntity<ItemEntity> getProduct(@PathVariable("item_id") Long id) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(itemManagementOutputPort.getItem(id));

  }

  @DeleteMapping(value = "{item_id}")
  public ResponseEntity<ItemEntity> removeProduct(@PathVariable("item_id") Long id) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(itemManagementOutputPort.removeItem(id));

  }

  @PutMapping(value = "/{item_id}")
  public ResponseEntity<ItemEntity> updateProduct(
          @PathVariable("item_id") Long id,
          @RequestBody ItemEntity itemEntity
  ) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(itemManagementOutputPort.updateItem(id, itemEntity));

  }

}