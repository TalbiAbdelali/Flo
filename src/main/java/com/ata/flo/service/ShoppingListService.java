package com.ata.flo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ata.flo.dao.ShoppingListDao;
import com.ata.flo.model.Product;
import com.ata.flo.model.ShoppingList;
import com.ata.flo.model.User;

@Service
public class ShoppingListService {
	
	private final ShoppingListDao shoppingListDao;
	
	@Autowired
	public ShoppingListService(@Qualifier("ShoppingListPsqlTable") ShoppingListDao shoppingListDao) {
		super();
		this.shoppingListDao = shoppingListDao;
	}
	
	
	
	public int createShoppingList(ShoppingList list /*, List<Product> products*/) {
		return shoppingListDao.createShoppingList(list);
	}
	
	public List<ShoppingList> selectAllLists(){
		return shoppingListDao.selectAllLists();
	}
	
	public Optional<ShoppingList> getShoppingListById(String id) {
		return shoppingListDao.selectListById(id);
	}
	
	public int deleteProductById(String id) {
		return shoppingListDao.deleteProductBYId(id);
	}
	
	public int updateProductById(String id, Product product) {
		return shoppingListDao.updateProductById(id, product);
	}
	
	public int addProduct(String shoppingId, Product product) {
		return  shoppingListDao.insertProduct(shoppingId, product);
	}
	
}
