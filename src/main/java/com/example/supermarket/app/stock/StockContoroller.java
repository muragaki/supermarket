package com.example.supermarket.app.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.supermarket.domain.model.GoodsOrder;
import com.example.supermarket.domain.service.goods.GoodsService;
import com.example.supermarket.domain.service.order.OrderService;

/**
 * 在庫処理コントローラー
 * 
 * @author Muragaki
 *
 */
@Controller
@RequestMapping("stock")
public class StockContoroller {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	
	@GetMapping("order")
	String order(StockForm stockForm, Model model) {
		stockForm.setStockList(goodsService.findGoods());
		return "stock/order";
	}
	
	@PostMapping("putorder")
	String putOrder(OrderForm orderForm, Model model) {
		orderService.save(orderForm.getItemcode(), orderForm.getOrder());
		return "redirect:/stock/order";
	}

	@PostMapping("sendorder")
	String sendOrder(OrderForm orderForm, Model model) {
		List<GoodsOrder> a = orderService.findAll();
		return "redirect:stock/order";
	}
	
	@PostMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		return "redirect:/";
	}
}