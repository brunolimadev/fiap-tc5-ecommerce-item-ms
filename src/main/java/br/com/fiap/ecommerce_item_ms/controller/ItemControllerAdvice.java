package br.com.fiap.ecommerce_item_ms.controller;

import br.com.fiap.ecommerce_item_ms.domain.entities.MessageEntity;
import br.com.fiap.ecommerce_item_ms.ports.exception.OutputPortException;
import br.com.fiap.ecommerce_item_ms.domain.exception.EntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static br.com.fiap.ecommerce_item_ms.utils.MessageEnumUtils.*;

@ControllerAdvice
public class ItemControllerAdvice {

  @ExceptionHandler(
          {
                  EntityException.class
          }
  )
  public ResponseEntity<MessageEntity> handleBadRequestWithDomainException(RuntimeException exception) {

    return ResponseEntity
            .badRequest()
            .body(
                    MessageEntity
                            .builder()
                            .title(TIME_DOMAIN_EXCEPTION)
                            .message(exception.getMessage())
                            .build()
            );

  }

  @ExceptionHandler(
          {
                  HttpMessageNotReadableException.class,
          }
  )
  public ResponseEntity<MessageEntity> handleBadRequestWithSpringException(RuntimeException exception) {

    return ResponseEntity
            .badRequest()
            .body(
                    MessageEntity
                            .builder()
                            .title(TITLE_DEFAULT_EXCEPTION)
                            .message(MESSAGE_EMPTY_BODY_SPRING_EXCEPTION.getMessage())
                            .build()
            );

  }

  @ExceptionHandler(
          {
                  OutputPortException.class
          }
  )
  public ResponseEntity<MessageEntity> handleUnProcessableEntity(RuntimeException exception) {

    return ResponseEntity
            .unprocessableEntity()
            .body(
                    MessageEntity
                            .builder()
                            .title(TITLE_PORTS_EXCEPTION)
                            .message(exception.getMessage())
                            .build()
            );

  }

}