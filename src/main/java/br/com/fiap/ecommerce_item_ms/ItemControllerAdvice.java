package br.com.fiap.ecommerce_item_ms;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static br.com.fiap.ecommerce_item_ms.MessageEnumUtils.*;

@ControllerAdvice
public class ItemControllerAdvice {

  @ExceptionHandler(
          {
                  EntityException.class,
                  UseCaseException.class,
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
                  InputPortException.class,
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