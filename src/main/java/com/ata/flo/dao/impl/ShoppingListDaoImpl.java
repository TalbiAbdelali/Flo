package com.ata.flo.dao.impl;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ata.flo.dao.ShoppingListDao;
import com.ata.flo.model.Product;
import com.ata.flo.model.ShoppingList;

@Repository("ShoppingListPsqlTable")
public class ShoppingListDaoImpl implements ShoppingListDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ShoppingListDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<Product> selectProductById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProductBYId(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProductById(String id, Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertProduct(String shoppingId, Product product) {
		final String sql = "insert into products(productid, name ,quantity, price, invoice, shoppingid) values(?,?,?,?,?,?)";
		return this.jdbcTemplate.update(sql,
				new Object[]{
						UUID.randomUUID().toString(),
						product.getName(),
						product.getQuantity(),
						product.getPrice(),
						product.getInvoice(),
						shoppingId
						});
	}

	@Override
	public int createShoppingList(ShoppingList list /*, List<Product> products*/ ) {
		String sql = "insert into ShoppingLists(shoppingid, totalPrice) values(?,?)";
		String shoppingId = UUID.randomUUID().toString();
		double totalPrice = 0;
		this.jdbcTemplate.update(sql,
				new Object[]{
						shoppingId,
						totalPrice
						});
		for(Product p : list.getList()) {
			insertProduct(shoppingId, p);
			totalPrice =+ p.getPrice();
		}
		
		sql ="UPDATE ShoppingLists SET totalPrice = ?  WHERE shoppingid = ?";
		Object[] params = {totalPrice, shoppingId};
        int[] types = {Types.DOUBLE, Types.VARCHAR};
		this.jdbcTemplate.update(sql, params, types);	
		
		return 1;
	}

	@Override
	public List<ShoppingList> selectAllLists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShoppingList> selectListById(String id) {
		final String sql ="SELECT shoppingid, totalPrice FROM ShoppingLists WHERE shoppingid = ?";
		ShoppingList list = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
 			String listId = resultSet.getString("shoppingid");
 			double totalPrice = resultSet.getDouble("totalPrice");
 			return new ShoppingList(listId, selectAllProducts(listId), totalPrice);
 		});
 		return Optional.ofNullable(list);
	}

	@Override
	public List<Product> selectAllProducts(String shoppingId) {
		final String sql ="SELECT productid, name, quantity, price, invoice FROM  Products where shoppingid = ?";
 		return jdbcTemplate.query(sql, new Object[]{shoppingId}, (resultSet, i) -> {
 			String productId = resultSet.getString("productid");
 			String name = resultSet.getString("name");
 			double quantity = resultSet.getDouble("quantity");
 			double price = resultSet.getDouble("price");
 			String invoice = resultSet.getString("invoice");
 			return new Product(productId, name ,quantity, price, invoice);
 		});
	}
	
}
