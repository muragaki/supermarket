package com.example.supermarket.app.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.supermarket.domain.service.goods.GoodsService;

/**
 * コントローラー
 * スーパーマーケットのメインコントローラー
 * 
 * @author Muragaki
 * @version 1.0
 */
@Controller
public class GoodsController {
	@Autowired
	GoodsService goodsService;
	
	/**
	 * メソッド：商品一覧表示
	 * 全商品情報の取得
	 * 
	 * @param model
	 * @return goods/goods View
	 */
	@GetMapping("goods")
	String goods(Model model) {
		
		model.addAttribute("goods", goodsService.findGoods());
		return "goods/goods";
	}
}
