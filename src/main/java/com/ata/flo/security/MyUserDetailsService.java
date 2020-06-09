package com.ata.flo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ata.flo.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;

import static com.ata.flo.security.ApplicationUserRole.*;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private final PasswordEncoder passwordEncoder;
	
	private UserDao userDao;
	
	@Autowired
	public MyUserDetailsService(PasswordEncoder passwordEncoder,@Qualifier("UserPsqlTable") UserDao userDao) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.authorities(ADMIN.getGrantedAuthorities())
				.build();
		
		com.ata.flo.model.User user = this.userDao.selectUserByUsername(username).
										orElseThrow(() -> new UsernameNotFoundException("error"));
		
		ApplicationUserMain mainUser = new ApplicationUserMain(user);
		return mainUser;
	}

}
