package com.example.supermarket.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_stock")
public class Stock {
	@Id
	private String itemcode;
	private Integer stock;
	private Integer standard_stock;
	private Integer orders;
	private LocalDateTime timestamp;
}