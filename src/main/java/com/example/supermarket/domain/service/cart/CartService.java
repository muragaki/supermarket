package com.example.supermarket.domain.service.cart;

import java.util.List;

import com.example.supermarket.domain.model.Cart;

public interface CartService {
	public void save(Cart cart);
	public List<Cart> finditem(String userid);
	public void update(Cart cart);
	public void delete(Cart cart);

}
