package com.example.supermarket.domain.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermarket.domain.model.Cart;
import com.example.supermarket.domain.repository.cart.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository cartRepository;

	public void save(Cart cart) {
		if (cartRepository.readByItemcodeAndUserid(cart.getItemcode(), cart.getUserid()) == null) {
			cartRepository.save(cart);
		} else {
			// 既に登録されている場合は登録しない
		}
	}
	
	public List<Cart> finditem(String userid) {
		return cartRepository.findByUseridOrderByCartIdAsc(userid);
	}
	
	public void update(Cart cart) {
		if (cart.getSales() == 0) {
			cartRepository.deleteById(cart.getCartId());
		} else {
			Cart tempsales = cartRepository.findById(cart.getCartId()).get();
			tempsales.setSales(cart.getSales());
			cartRepository.save(tempsales);
		}
	}
	public void delete(Cart cart) {
		cartRepository.deleteById(cart.getCartId());
	}
}