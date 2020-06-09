package com.ata.flo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ata.flo.dao.UserDao;
import com.ata.flo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private UserDao userDao;
	
	@Autowired
	public MyUserDetailsService(@Qualifier("UserPsqlTable") UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userDao.selectUserByUsername(username).
										orElseThrow(() -> new UsernameNotFoundException(String.format("User not found" + username)));
		ApplicationUserMain mainUser = new ApplicationUserMain(user);
		return mainUser;
	}

}
