package com.ata.flo.model;

import javax.validation.constraints.NotBlank;

public class Product {
	private final String id;
	@NotBlank
	private final String name;
	@NotBlank
	private final double quantity;
	@NotBlank
	private final double price;
	
	private final String invoice;
	
	public Product(String id, String name, double quantity, double price, String invoice) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.invoice = invoice;
	}

	public String getId() {
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
