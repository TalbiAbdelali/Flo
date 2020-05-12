package com.ata.flo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ata.flo.dao.UserDao;
import com.ata.flo.model.User;

@Service
public class UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserService(@Qualifier("psqlDao") UserDao userDao) {
		this.userDao = userDao;
	}
	
	public int addUser(User user) {
		return this.userDao.insertUser(user);
	}
	
	public List<User> getAllUsers(){
		return userDao.selectAllUsers();
	}
	
	public Optional<User> getUserById(UUID id){
		return userDao.selectUserById(id);
	}
	
	public int deleteUser(UUID id) {
		return userDao.deleteUserBYId(id);
	}
	
	public int updateUser(UUID id, User user) {
		return userDao.updateUserById(id, user);
	}
	
}
