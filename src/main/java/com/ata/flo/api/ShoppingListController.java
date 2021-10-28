package com.ata.flo.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ata.flo.model.Product;
import com.ata.flo.model.ShoppingList;
import com.ata.flo.model.User;
import com.ata.flo.service.ShoppingListService;

@RestController
@RequestMapping("api/shoplist")
public class ShoppingListController {
	
	private final ShoppingListService shoppingListService;

	public ShoppingListController(ShoppingListService shoppingListService) {
		super();
		this.shoppingListService = shoppingListService;
	}
	
	@PostMapping
	public int createShoppingList(@Valid @NotNull @RequestBody ShoppingList list) {
		return shoppingListService.createShoppingList(list);
	}
	
	@GetMapping
	public List<ShoppingList> selectAllLists(){
		return shoppingListService.selectAllLists();
	}
	
	@GetMapping(path = "{shoppingId}")
	public ShoppingList getShoppingListById(@PathVariable("shoppingId") String id) {
		return shoppingListService.getShoppingListById(id)
				.orElse(null);
	}
	
	@PostMapping(path = "product/{shoppingId}")
	public int addProduct(@PathVariable("shoppingId") String shoppingId, /*@Valid @NotNull*/ @RequestBody Product product) {
		return shoppingListService.addProduct(shoppingId, product);
	}
	
	@DeleteMapping(path = "product/{productId}")
	public int deleteProductById(@PathVariable("productId") String id) {
		return shoppingListService.deleteProductById(id);
	}
	
	@PutMapping(path = "product/{productId}")
	public int updateProductById(@PathVariable("productId") String id, @RequestBody Product product) {
		return shoppingListService.updateProductById(id, product);
	}
}
