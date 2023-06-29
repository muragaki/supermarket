package com.example.supermarket.domain.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermarket.domain.model.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {

}