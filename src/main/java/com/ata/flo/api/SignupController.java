package com.ata.flo.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ata.flo.model.User;
import com.ata.flo.service.UserService;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class SignupController {
	
	private final UserService userService;
	
	public SignupController(UserService userService) {
		super();
		this.userService = userService;
	}

	
	@PostMapping("signup")
	public ResponseEntity<Object> signup(@Valid @NotNull @RequestBody User user) throws Exception{
		try {
			if(this.userService.addUser(user) >= 1) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new Object[]{"User has been created."});
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object[]{"User hasn't been created!"});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("exist")
	@ResponseBody
	public Boolean checkEmailExisting(@RequestParam  String email) {
		/*if (this.userService.isEmailExiste(email)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email already existe" );
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Ok" );
		}*/
		return this.userService.isEmailExiste(email);
	}
}
