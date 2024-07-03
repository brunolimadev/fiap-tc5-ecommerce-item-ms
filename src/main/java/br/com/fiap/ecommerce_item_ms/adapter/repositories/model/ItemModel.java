package br.com.fiap.ecommerce_item_ms.adapter.repositories.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "items")
public class ItemModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Double price;

  @Column(name = "store_quantity")
  private Integer storeQuantity;

  @Column(name = "create_datetime")
  private LocalDateTime createDateTime;

  @Column(name = "update_datetime")
  private LocalDateTime updateDateTime;

}