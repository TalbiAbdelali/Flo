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

import com.ata.flo.model.User;
import com.ata.flo.service.UserService;

@RestController
@RequestMapping("admin/api/user")
public class UserManagementController {
	
	private final UserService userService;

	public UserManagementController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping
	public void  addUser(@Valid @NotNull @RequestBody User user) {
		this.userService.addUser(user);
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping(path = "{id}")
	public User getUserById(@PathVariable("id") String id) {
		return userService.getUserById(id)
				.orElse(null);
	}
	
	@DeleteMapping(path ="{id}")
	public void deletePersonById(@PathVariable("id") String id) {
		userService.deleteUser(id);
	}
	
	@PutMapping(path = "{id}")
	public void updatePersonById(@PathVariable("id") String id,/*@Valid @NotNull*/ @RequestBody User user) {
		userService.updateUser(id, user);
	}
}
