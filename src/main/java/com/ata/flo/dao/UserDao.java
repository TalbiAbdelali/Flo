package com.ata.flo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ata.flo.model.User;

public interface UserDao {
	int insertUser(String id,  User user);
	
	default int insertUser(User user) {
		String id = UUID.randomUUID().toString();
		return insertUser(id, user);
	}
	
	List<User> selectAllUsers();
	
	Optional<User> selectUserById(String id);
	
	// For DB Authentication
	Optional<User> selectUserByUsername(String username);
	
	int deleteUserBYId(String id);
	
	int updateUserById(String id, User user);
}
