package dev.matheusvictor.santanerdevweek.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExceptionDetails {
  private String title;
  private int status;
  private String developerMessage;
  private String details;
  private LocalDate timestamp;
}
