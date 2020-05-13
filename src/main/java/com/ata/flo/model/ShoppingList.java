package com.ata.flo.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class ShoppingList {
	private final String id;
	private final List<Product> list;
	private final double totalPrice;
	
	public ShoppingList(String id, List<Product> list, double totalPrice) {
		super();
		this.id = id;
		this.list = list;
		this.totalPrice = totalPrice;
	}

	public String getId() {
		return id;
	}

	public List<Product> getList() {
		return list;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
}
