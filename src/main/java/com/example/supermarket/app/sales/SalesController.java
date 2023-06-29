package com.example.supermarket.app.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.supermarket.domain.model.Sales;
import com.example.supermarket.domain.service.sales.SalesService;
import com.example.supermarket.domain.service.user.SuperUserDetails;

@Controller
public class SalesController {
	
	@Autowired
	SalesService salesService;
	
	@GetMapping("/sales")
	String sales(@ModelAttribute("salesForm") SalesForm salesForm, Model model) {
		String userId = ((SuperUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserId();
		List<Sales> salesList = salesService.finditem(userId);
		salesForm.setSalesList(salesList);
		return "sales/sales";
	}

}
