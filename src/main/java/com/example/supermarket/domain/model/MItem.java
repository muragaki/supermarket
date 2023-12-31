package com.example.supermarket.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity
 * 商品情報
 * 
 * @author Muragaki
 * @version 1.0
 */
@Data
@Entity
@Table(name = "m_item")
public class MItem {
	@Id
	private String itemcode;
	private String itemname;
	private Integer itemprice;
	private String itempicture;
	@OneToOne
	@JoinColumn(name="itemcode", insertable=false, updatable=false)
	private Stock stock;
	private boolean enableflag;
}