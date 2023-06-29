package com.example.supermarket.app.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.supermarket.domain.service.goods.GoodsService;

@Controller
@RequestMapping("stock")
public class StockContoroller {
	
	@Autowired
	GoodsService goodsService;

	
	@GetMapping("order")
	String order(OrderForm orderForm, Model model) {
		orderForm.setStockList(goodsService.findGoods());
		return "stock/order";
	}

}
