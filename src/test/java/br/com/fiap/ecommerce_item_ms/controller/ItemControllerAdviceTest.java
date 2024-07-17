package br.com.fiap.ecommerce_item_ms.controller;

import br.com.fiap.ecommerce_item_ms.domain.exception.EntityException;
import br.com.fiap.ecommerce_item_ms.ports.exception.OutputPortException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.assertj.core.api.Assertions.assertThat;

class ItemControllerAdviceTest {

  @Test
  void shouldHandleBadRequestWithDomainException() {

    //Arrange
    var itemControllerAdvice = new ItemControllerAdvice();
    var exception = new EntityException("Error");

    //Act
    var response = itemControllerAdvice.handleBadRequestWithDomainException(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);

  }

  @Test
  void shouldHandleBadRequestWithSpringException() {

    //Arrange
    var itemControllerAdvice = new ItemControllerAdvice();
    var exception = new HttpMessageNotReadableException("Error");

    //Act
    var response = itemControllerAdvice.handleBadRequestWithSpringException(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);

  }

  @Test
  void shouldHandleUnProcessableEntity() {

    //Arrange
    var itemControllerAdvice = new ItemControllerAdvice();
    var exception = new OutputPortException("Error");

    //Act
    var response = itemControllerAdvice.handleUnProcessableEntity(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

  }

}