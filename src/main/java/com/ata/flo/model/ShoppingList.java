package com.ata.flo.model;

import java.util.List;
import java.util.UUID;

public class ShoppingList {
	private final UUID id;
	private final List<Product> list;
	private final double totalPrice;
	
	public ShoppingList(UUID id, List<Product> list, double totalPrice) {
		super();
		this.id = id;
		this.list = list;
		this.totalPrice = totalPrice;
	}

	public UUID getId() {
		return id;
	}

	public List<Product> getList() {
		return list;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
}
