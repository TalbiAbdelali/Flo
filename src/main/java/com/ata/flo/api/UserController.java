package com.ata.flo.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ata.flo.model.User;
import com.ata.flo.service.UserService;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private final UserService userService;	

	public UserController(UserService userSevice) {
		this.userService = userSevice;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
    @GetMapping(path="user")
    public User getUser(@RequestParam(value="username") String username) {
        return this.userService.getUserByUsername(username).orElse(null);
    }
    
    @GetMapping(path="user/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return this.userService.getUserById(id).orElse(null);
    }
    
    @PutMapping(path = "{id}")
	public void updatePersonById(@PathVariable("id") String id,/*@Valid @NotNull*/ @RequestBody User user) {
		this.userService.updateUser(id, user);
	}
    
    @GetMapping(path = "usernameExist")
    public Boolean isUsernameExist(@RequestParam String username) {
    	return this.userService.isUsernameExist(username);
    }
}