package com.example.supermarket.domain.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermarket.domain.model.GoodsOrder;

public interface OrderRepository extends JpaRepository<GoodsOrder, String> {
}