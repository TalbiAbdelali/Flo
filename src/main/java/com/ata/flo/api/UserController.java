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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ata.flo.model.User;
import com.ata.flo.service.UserService;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private final UserService userSevice;	

	public UserController(UserService userSevice) {
		this.userSevice = userSevice;
	}



	/*@PostMapping("login")
	public boolean login(@RequestBody User user) {
		HttpHeaders resHeader = new HttpHeaders();

        resHeader.set("origin", "http://localhost:8080");
        resHeader.set("Content-Type", "application/json");
        resHeader.set("Accept", "application/json");
        resHeader.set("Authorization", "");
        
		return
          user.getUsername().equals("user") && user.getPassword().equals("password");
    }*/
	
    @RequestMapping("user")
    public User getUser(@RequestParam String username) {
        return this.userSevice.getUserByUsername(username).orElse(null);
    }
}
