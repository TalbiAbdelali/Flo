package com.ata.flo.dao.impl;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ata.flo.api.mapper.ProductRowMapper;
import com.ata.flo.dao.ShoppingListDao;
import com.ata.flo.model.Product;
import com.ata.flo.model.ShoppingList;
import com.ata.flo.model.User;

@Repository("ShoppingListPsqlTable")
public class ShoppingListDaoImpl implements ShoppingListDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ShoppingListDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<Product> selectProductById(String productid) {
		final String sql ="SELECT productid, name, quantity, price, invoice FROM  Products where productid = ?";
 		Product product =  jdbcTemplate.queryForObject(sql, new Object[]{productid}, (resultSet, i) -> {
 			String productId = resultSet.getString("productid");
 			String name = resultSet.getString("name");
 			double quantity = resultSet.getDouble("quantity");
 			double price = resultSet.getDouble("price");
 			String invoice = resultSet.getString("invoice");
 			return new Product(productId, name ,quantity, price, invoice);
 		});
 		return Optional.ofNullable(product);
	}

	@Override
	public int deleteProductBYId(String id) {
		final String sql ="DELETE FROM products WHERE productid = ?";
		
		Object[] params = {id};
        int[] types = {Types.VARCHAR};
        
		return this.jdbcTemplate.update(sql, params, types);
	}

	@Override
	public int updateProductById(String id, Product product) {
		
		final String sql ="UPDATE products SET name = ?, quantity = ?, price = ?, invoice = ?  WHERE productid = ?";
		
		Object[] params = {product.getName(), product.getQuantity(), product.getPrice(), product.getInvoice(), id};
        int[] types = {Types.VARCHAR, Types.DOUBLE, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR};
        
		return this.jdbcTemplate.update(sql, params, types);
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
	public int createShoppingList(ShoppingList list /*, List<Product> products*/  ) {
		String sql = "insert into ShoppingLists(shoppingid, totalPrice) values(?,?)";
		String shoppingId = UUID.randomUUID().toString();
		double totalPrice = 0.0;
		this.jdbcTemplate.update(sql,
				new Object[]{
						shoppingId,
						totalPrice
						});
		if(list.getList()!=null) {
			for(Product p : list.getList()) {
				insertProduct(shoppingId, p);
				totalPrice += p.getPrice();
			}
		}
		
		sql ="UPDATE ShoppingLists SET totalPrice = ?  WHERE shoppingid = ?";
		Object[] params = {totalPrice, shoppingId};
        int[] types = {Types.DOUBLE, Types.VARCHAR};
		this.jdbcTemplate.update(sql, params, types);	
		
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingList> selectAllLists() {
		final String sql ="SELECT * FROM  shoppinglists";
 		return jdbcTemplate.query(sql, (resultSet, i) -> {
 			String shoppingid = resultSet.getString("shoppingid"); 			
 			 			
 			return selectListById(shoppingid).orElse(null);
 		});
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
