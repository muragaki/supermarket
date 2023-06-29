package com.example.supermarket.app.stock;

import java.util.ArrayList;
import java.util.List;

import com.example.supermarket.domain.model.MItem;

import lombok.Data;

@Data
public class OrderForm {
	private List<MItem> stockList = new ArrayList<>();
}
