package dev.matheusvictor.santanerdevweek.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String number;

  @Column
  private String agency;

  @Column(nullable = false, scale = 2, precision = 13)
  private BigDecimal balance;

  @Column(name = "additional_limit", nullable = false, scale = 2, precision = 13)
  private BigDecimal limit;
}
