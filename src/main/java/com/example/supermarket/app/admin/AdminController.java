package com.example.supermarket.app.admin;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.supermarket.app.sales.SalesForm;
import com.example.supermarket.app.stock.StockForm;
import com.example.supermarket.domain.model.MItem;
import com.example.supermarket.domain.model.RoleName;
import com.example.supermarket.domain.model.User;
import com.example.supermarket.domain.service.goods.GoodsService;
import com.example.supermarket.domain.service.sales.SalesService;
import com.example.supermarket.domain.service.stock.StockService;
import com.example.supermarket.domain.service.user.SuperUserDetailsService;

/**
 * コントローラー
 * 管理者権限機能
 * 
 * @author Muragaki
 * @version 1.0
 */
@Controller
public class AdminController {
	
	@Autowired
	SuperUserDetailsService superUserDetailsService;	// ユーザサービス
	
	@Autowired
	GoodsService goodsService;	// 商品サービス
	
	@Autowired
	SalesService salesService;	// 売上サービス
	
	@Autowired
	StockService stockService;	// 在庫サービス

	/**
	 * 管理者メニュー表示
	 * 
	 * @return admin/adminmenu View
	 */
	@GetMapping("admin")
	String admin() {
		return "admin/adminmenu";
	}

	/**
	 * 新規ユーザー登録画面表示
	 * 
	 * @param userForm
	 * @param model
	 * @return admin/newuser
	 */
	@GetMapping("admin/newuser")
	String newuser(UserForm userForm, Model model) {
		userForm.setUsername("username");
		userForm.setPassword("password");
		userForm.setFirstname("姓");
		userForm.setLastname("名");
		userForm.setRolename(RoleName.ADMIN);
		userForm.setRoleNameList(new ArrayList<RoleName>(Arrays.asList(RoleName.ADMIN,RoleName.USER)));
		return "admin/newuser";
	}
	
	/**
	 * 新規ユーザー登録
	 * 
	 * @param userForm
	 * @param br
	 * @param model
	 * @return admin/signup View
	 */
	@PostMapping("admin/signup")
	String signup(@ModelAttribute("userForm") @Validated UserForm userForm, BindingResult br, Model model) {
		if (br.hasErrors()) {
			userForm.setRoleNameList(new ArrayList<RoleName>(Arrays.asList(RoleName.ADMIN,RoleName.USER)));
			return "admin/newuser";
		}
		User user = new User(userForm.getUsername(),
				userForm.getPassword(),
				userForm.getFirstname(),
				userForm.getLastname(),
				userForm.getRolename());
		
		superUserDetailsService.userregist(user);
		model.addAttribute("userForm", userForm);
		return "admin/signup";
	}
	
	/**
	 * ユーザ一覧表示
	 * 
	 * @param model
	 * @return admin/userlist View
	 */
	@GetMapping("admin/userlist")
	String userlist(Model model) {
		List<User> userlist = superUserDetailsService.getUserAll();
		model.addAttribute("userlist", userlist);
		return "admin/userlist";
	}
	
	/**
	 * ユーザー編集画面表示
	 * 
	 * @param userId
	 * @param userForm
	 * @param model
	 * @return admin/useredit View
	 */
	@GetMapping("admin/edit")
	String useredit(@RequestParam("userId") String userId, UserEditForm userForm, Model model) {
		User user = superUserDetailsService.findById(userId);
		userForm.setUsername(user.getUserId());
		userForm.setFirstname(user.getFirstName());
		userForm.setLastname(user.getLastName());
		userForm.setRolename(user.getRoleName());
		userForm.setRoleNameList(new ArrayList<RoleName>(Arrays.asList(RoleName.ADMIN,RoleName.USER)));
		model.addAttribute("userForm", userForm);

		return "admin/useredit";
	}
	
	/**
	 * ユーザー変更登録・確認
	 * 
	 * @param userForm
	 * @param br
	 * @param model
	 * @return admin/editconf View
	 */
	@PostMapping("admin/editconf")
	String editconf(@ModelAttribute("userForm") @Validated UserEditForm userForm, BindingResult br, Model model) {
		if (br.hasErrors()) {
			userForm.setRoleNameList(new ArrayList<RoleName>(Arrays.asList(RoleName.ADMIN,RoleName.USER)));
			return "admin/useredit";
		}
		User user = superUserDetailsService.findById(userForm.getUsername());
		if (!user.getFirstName().equals(userForm.getFirstname())) {
			user.setFirstName(userForm.getFirstname());
		}
		if (!user.getLastName().equals(userForm.getLastname())) {
			user.setLastName(userForm.getLastname());
		}
		if (!user.getRoleName().getValue().equals(userForm.getRolename().getValue())) {
			user.setRoleName(userForm.getRolename());
		}
		superUserDetailsService.userupdateregist(user);
		model.addAttribute("userForm", userForm);
		return "admin/editconf";
	}
	
	/**
	 * 商品情報一覧
	 * 
	 * @param model
	 * @return admin/goodslist
	 */
	@GetMapping("admin/goodslist")
	String goodslist(Model model) {
		List<MItem> goodslist = goodsService.findGoods();
		model.addAttribute("goodsList", goodslist);
		return "admin/goodslist";
	}
	
	/**
	 * 編集用商品情報表示
	 * 
	 * @param itemcode
	 * @param itemEditForm
	 * @param model
	 * @return admin/itemedit
	 */
	@GetMapping("admin/itemedit")
	String itemedit(@RequestParam("itemcode") String itemcode, ItemEditForm itemEditForm, Model model) {
		MItem item = goodsService.findItem(itemcode);
		itemEditForm.setItemcode(item.getItemcode());
		itemEditForm.setItemname(item.getItemname());
		itemEditForm.setItemprice(item.getItemprice());
		itemEditForm.setEnableflag(item.isEnableflag());
//		model.addAttribute("item", itemEditForm);
		return "admin/itemedit";
	}
	
	/**
	 * 商品情報更新・確認
	 * 
	 * @param itemEditForm
	 * @param br
	 * @param model
	 * @return admin/itemeditconf
	 */
	@PostMapping("admin/itemeditconf")
	String itemeditconf(@ModelAttribute("itemEditForm") @Validated ItemEditForm itemEditForm, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "admin/itemedit";
		}
		MItem item = goodsService.findItem(itemEditForm.getItemcode());
		if (!item.getItemname().equals(itemEditForm.getItemname())) {
			item.setItemname(itemEditForm.getItemname());
		}
		if (item.getItemprice() != itemEditForm.getItemprice()) {
			item.setItemprice(itemEditForm.getItemprice());
		}
		if (item.isEnableflag() != itemEditForm.isEnableflag()) {
			item.setEnableflag(itemEditForm.isEnableflag());
		}
		goodsService.itemregist(item);
		return "admin/itemeditconf";
	}
	
	/**
	 * 売上一覧表示
	 * 
	 * @param salesForm
	 * @param model
	 * @return
	 */
	@GetMapping("admin/saleslist")
	String saleslist(SalesForm salesForm, Model model) {
		salesForm.setSalesList(salesService.findSalesAll());
		salesForm.setUserList(superUserDetailsService.getUserAll());
		String today = LocalDateTime.now().toString().substring(0, 10);
		model.addAttribute("today", today);
		return "admin/saleslist";
	}
	
	/**
	 * 売上検索
	 * 
	 * @param searchForm
	 * @param br
	 * @param salesForm
	 * @param model
	 * @return
	 */
	@GetMapping("admin/saleslist/serch")
	String serch(@ModelAttribute("searchForm") @Validated SearchForm searchForm, BindingResult br,
			SalesForm salesForm, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("today", LocalDateTime.now().toString().substring(0, 10));
			return "admin/saleslist";
		}
		if (searchForm.getUserid().equals("all")) {
		//	salesForm.setSalesList(salesService.findSalesAll());
			salesForm.setSalesList(salesService.findSalesDate(LocalDateTime.of(searchForm.getFirstDate(), LocalTime.of(0, 0)),
					LocalDateTime.of(searchForm.getLastDate(), LocalTime.of(23, 59))));
		} else {
			salesForm.setSalesList(salesService.findUserDate(searchForm.getUserid(), 
					LocalDateTime.of(searchForm.getFirstDate(), LocalTime.of(0, 0)),
					LocalDateTime.of(searchForm.getLastDate(), LocalTime.of(23, 59))));
		}
		salesForm.setUserList(superUserDetailsService.getUserAll());
		model.addAttribute("today", LocalDateTime.now().toString().substring(0, 10));
		return "admin/salesList";
	}
	
	/**
	 * 在庫表示
	 * 
	 * @param stockForm
	 * @param model
	 * @return
	 */
	@GetMapping("admin/stocklist")
	String stocklist(StockForm stockForm, Model model) {
		stockForm.setStockList(goodsService.findGoods());
		return "admin/stocklist";
	}
}