package com.ata.flo.model;

import java.util.UUID;

public class Product {
	private final UUID id;
	private final String name;
	private final double quantity;
	private final double price;
	private final String invoice;
	
	public Product(UUID id, String name, double quantity, double price, String invoice) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.invoice = invoice;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public String getInvoice() {
		return invoice;
	}
	
	
}
