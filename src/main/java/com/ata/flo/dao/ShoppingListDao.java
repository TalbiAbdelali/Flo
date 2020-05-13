package com.ata.flo.dao;

import java.util.List;
import java.util.Optional;

import com.ata.flo.model.Product;
import com.ata.flo.model.ShoppingList;

public interface ShoppingListDao {
	
	int createShoppingList(ShoppingList list /*, List<Product> products*/);
	
	List<ShoppingList> selectAllLists();
	
	Optional<ShoppingList> selectListById(String id);
	
	int insertProduct(String shoppingId,  Product product);
	
	List<Product> selectAllProducts(String shoppingId);
	
	Optional<Product> selectProductById(String id);
	
	int deleteProductBYId(String id);
	
	int updateProductById(String id, Product product);
}
