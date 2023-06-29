package com.example.supermarket.domain.repository.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermarket.domain.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Cart> findByUseridOrderByCartIdAsc(String userid);
	Cart readByItemcode(String itemcode);
	Cart readByItemcodeAndUserid(String itemcode, String userid);
}