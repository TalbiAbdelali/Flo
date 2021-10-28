package com.ata.flo.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ata.flo.model.User;
import com.ata.flo.service.UserService;

@RestController
@RequestMapping("/api/")
public class SignupController {
	
	private final UserService userService;
	
	public SignupController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("signup")
	public int signup(@Valid @NotNull @RequestBody User user) {
		return this.userService.addUser(user);
	}
}
