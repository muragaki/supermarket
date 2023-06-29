package com.example.supermarket.app.sales;

import java.util.ArrayList;
import java.util.List;

import com.example.supermarket.domain.model.Sales;
import com.example.supermarket.domain.model.User;

import lombok.Data;

@Data
public class SalesForm {
	private List<Sales> salesList = new ArrayList<>();
	private List<User> userList = new ArrayList<>();
}
