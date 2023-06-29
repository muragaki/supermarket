package com.example.supermarket.app.cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.supermarket.domain.model.Cart;
import com.example.supermarket.domain.model.Sales;
import com.example.supermarket.domain.service.cart.CartService;
import com.example.supermarket.domain.service.sales.SalesService;
import com.example.supermarket.domain.service.user.SuperUserDetails;

/**
 * コントローラー
 * 買い物カート
 * 
 * @author Muragaki
 * @version 1.0
 */
@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	SalesService salesService;
	
	/**
	 * カート登録情報取得
	 * ユーザーのカート登録商品情報を取得する
	 * 
	 * @param cartForm
	 * @param model
	 * @return
	 */
	@GetMapping("cart")
	String cart(@ModelAttribute("cartForm") CartForm cartForm, Model model) {
		String userId = ((SuperUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserId();
		List<Cart> itemlist = cartService.finditem(userId);
		cartForm.setCartList(new ArrayList<Cart>(itemlist));
		return "cart/cart";
	}
	
	/**
	 * カートへの商品情報登録
	 * 
	 * @param itemcode
	 * @param model
	 * @return
	 */
	@PostMapping("cartin")
	String cartin(String itemcode, Model model) {
		int one = 1;
		String userId = ((SuperUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserId();
		LocalDateTime localtime = LocalDateTime.now();
		
		Cart cart = new Cart(itemcode, one, userId, localtime);
		cartService.save(cart);
		return "redirect:/goods";
	}

	/**
	 * 合計金額の計算
	 * 
	 * @param cartForm
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("cartcalculate")
	String cartcalcurate(@ModelAttribute("cartForm") @Validated CartForm cartForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cart/cart";
		}
		// 合計金額計算
		Integer total = 0;
		ArrayList<Cart> cartList = cartForm.getCartList();
		for (Cart cart : cartList) {
			total += cart.getSales() * cart.getItem().getItemprice();
			cartService.update(cart);
		}
		model.addAttribute("total", total);
		return "cart/cartconf";
	}
	
	/**
	 * 注文・精算購入
	 * 
	 * @param cartForm
	 * @param model
	 * @return
	 */
	@PostMapping("cartconf")
	String cartconf(@ModelAttribute("cartForm") CartForm cartForm, Model model) {
		ArrayList<Cart> cartList = cartForm.getCartList();
		String userId = ((SuperUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserId();
		LocalDateTime localtime = LocalDateTime.now();	
	//	Date localtime = new Date();
		for (Cart cart : cartList) {
			Sales sales = new Sales(cart.getItemcode(), cart.getSales(), cart.getItem().getItemprice(), cart.getSales() * cart.getItem().getItemprice(),
					userId, localtime);
			salesService.save(sales);
			cartService.delete(cart);
		}
		return "redirect:/goods";
	}
}
