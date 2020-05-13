package com.ata.flo.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ata.flo.model.ShoppingList;
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
	
	@GetMapping(path = "{id}")
	public ShoppingList getShoppingListById(@PathVariable("id") String id) {
		return shoppingListService.getShoppingListById(id)
				.orElse(null);
	}
}
