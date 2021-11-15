package com.ata.flo.api;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ata.flo.model.User;
import com.ata.flo.service.UserService;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private final UserService userService;	

	public UserController(UserService userSevice) {
		this.userService = userSevice;
	}
	
    @GetMapping
    public User getUser(@RequestParam(required = true) String username) {
        return this.userService.getUserByUsername(username).orElse(null);
    }
    
    @PutMapping(path = "{id}")
	public void updatePersonById(@PathVariable("id") String id,/*@Valid @NotNull*/ @RequestBody User user) {
		this.userService.updateUser(id, user);
	}   
}