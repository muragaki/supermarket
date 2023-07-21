package com.example.supermarket.domain.service.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.supermarket.domain.model.GoodsOrder;
import com.example.supermarket.domain.repository.order.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public void save(String itemcode, Integer itemorder) {
		orderRepository.save( new GoodsOrder(itemcode, itemorder));
	}
	
	public List<GoodsOrder> findAll() {
		return orderRepository.findAll();
	}
	
	private String fileRead() {
		String result = "";
		ClassPathResource cr = new ClassPathResource("sample.txt");
		try {
			InputStream is = cr.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			// e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
}