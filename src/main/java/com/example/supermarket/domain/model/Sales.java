package com.example.supermarket.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "t_sales")
public class Sales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesId;
	private String itemcode;
	@ManyToOne
	@JoinColumn(name="itemcode", insertable=false, updatable=false)
	private MItem item;
	@Min(0)
	@Max(99)
	private Integer sales;
	private Integer price;
	private Integer amount;
	private String userid;
	@ManyToOne
	@JoinColumn(name="userid", insertable=false, updatable=false)
	private User user;
	private LocalDateTime salestime;
//	private Date salestime;
	
	public Sales() {
	}
	
	public Sales(String itemcode, Integer sales,Integer price, Integer amount, String userid, LocalDateTime localtime) {
		this.itemcode = itemcode;
		this.sales = sales;
		this.price = price;
		this.amount = amount;
		this.userid = userid;
		this.salestime = localtime;
	}
}
