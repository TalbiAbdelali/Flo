package com.ata.flo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ata.flo.dao.ShoppingListDao;
import com.ata.flo.model.ShoppingList;

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
	
	public Optional<ShoppingList> getShoppingListById(String id) {
		return shoppingListDao.selectListById(id);
	}
	
}
