package com.ata.flo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ata.flo.dao.UserDao;
import com.ata.flo.model.User;

@Service
public class UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserService(@Qualifier("UserPsqlTable") UserDao userDao) {
		this.userDao = userDao;
	}
	
	public int addUser(User user) throws Exception{
		try {
			return this.userDao.insertUser(user);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean isEmailExiste(String email) {
		return this.userDao.isEmailExiste(email);
	}
	
	public List<User> getAllUsers(){
		return userDao.selectAllUsers();
	}
	
	public Optional<User> getUserById(String id){
		return userDao.selectUserById(id);
	}
	
	public int deleteUser(String id) {
		return userDao.deleteUserBYId(id);
	}
	
	public int updateUser(String id, User user) {
		return userDao.updateUserById(id, user);
	}
	
}
