package dev.matheusvictor.santanerdevweek.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(IllegalAccessException.class)
  public ResponseEntity<ExceptionDetails> handleBusinessException(IllegalArgumentException businessException) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                    new ExceptionDetails("Illegal Argument Exception",
                            422,
                            "unprocessable entity",
                            "You can not create an existing account number" + businessException.getMessage(),
                            LocalDate.now()
                    )
            );
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ExceptionDetails> handleNotFoundException(NoSuchElementException notFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(
                    new ExceptionDetails("No such Element Exception",
                            404,
                            "Resource ID not found",
                            "NotFound" + notFoundException.getMessage(),
                            LocalDate.now()
                    )
            );
  }
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ExceptionDetails> handleUnexpectedException(Throwable businessException) {
    logger.error("Unexpected Error", businessException);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                    new ExceptionDetails(
                            "UnexpectedException",
                            500,
                            "",
                            "An UnexpectedException" + businessException.getMessage(),
                            LocalDate.now()
                    )
            );
  }
}
