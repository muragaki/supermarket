package com.example.supermarket.app.cart;

import java.util.ArrayList;

import com.example.supermarket.domain.model.Cart;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * カートForm
 * 
 * @author Muragaki
 * @version 1.0
 */
@Data
public class CartForm {
	@Valid
	private ArrayList<Cart> cartList = new ArrayList<>();
}
