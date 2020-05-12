package com.ata.flo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ata.flo.dao.UserDao;
import com.ata.flo.model.User;

@Repository("psqlDao")
public class UserDaoImpl implements UserDao{

	private static final List<User> DB = new ArrayList<User>();
	
	@Override
	public int insertUser(UUID id, User user) {
		DB.add(new User(id, user.getUsername(), user.getLastname(), user.getPassword(), user.getEmail()));
		return 1;
	}

	@Override
	public List<User> selectAllUsers() {
		return DB;
	}

	@Override
	public Optional<User> selectUserById(UUID id) {
		return DB.stream()
				.filter(user -> user.getId().equals(id))
				.findFirst();
	}

	@Override
	public int deleteUserBYId(UUID id) {
		Optional<User> userMaybe = selectUserById(id);
		if(userMaybe.isEmpty()) {
			return 0;
		}
		DB.remove(userMaybe.get());
		return 1;
	}

	@Override
	public int updateUserById(UUID id, User userUpdate) {
		return selectUserById(id)
				.map(p -> {
					int indexOfUserToDelete = DB.indexOf(p);
					if(indexOfUserToDelete >= 0) {
						DB.set(indexOfUserToDelete, new User(id, userUpdate.getUsername(), userUpdate.getLastname(), userUpdate.getPassword(), userUpdate.getEmail()));
						return 1;
					}
					return 0;
				})
				.orElse(0);
	}
}
