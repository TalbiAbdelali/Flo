package com.ata.flo.dao.impl;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ata.flo.dao.UserDao;
import com.ata.flo.model.User;

@Repository("UserPsqlTable")
public class UserDataAccessImpl implements UserDao{

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserDataAccessImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertUser(String id, User user) {
		final String sql = "insert into users(id, username, lastname, password, email) values(?,?,?,?,?)";
        return this.jdbcTemplate.update(sql, new Object[]{UUID.randomUUID().toString(), user.getUsername(), user.getLastname(), user.getPassword(), user.getEmail()});
	}

	@Override
	public List<User> selectAllUsers() {
		final String sql ="SELECT id, username, lastname, password, email FROM  users";
 		return jdbcTemplate.query(sql, (resultSet, i) -> {
 			String id = resultSet.getString("id");
 			String username = resultSet.getString("username");
 			String password = resultSet.getString("password");
 			String lastname = resultSet.getString("lastname");
 			String email = resultSet.getString("email");
 			return new User(id, username, lastname, password, email);
 		});
	}

	@Override
	public Optional<User> selectUserById(String id) {
		final String sql ="SELECT id, username, lastname, password, email FROM users WHERE id = ?";
 		User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
 			String userId = resultSet.getString("id");
 			String username = resultSet.getString("username");
 			String password = resultSet.getString("password");
 			String lastname = resultSet.getString("lastname");
 			String email = resultSet.getString("email");
 			return new User(userId, username, lastname, password, email);
 		});
 		return Optional.ofNullable(user);
	}

	@Override
	public int deleteUserBYId(String id) {
		final String sql ="DELETE FROM users WHERE id = ?";
		
		Object[] params = {id};
        int[] types = {Types.VARCHAR};
        
		return this.jdbcTemplate.update(sql, params, types);
	}

	@Override
	public int updateUserById(String id, User userUpdate) {
		final String sql ="UPDATE users SET username = ?, lastname = ?, password = ?, email = ?  WHERE id = ?";
		
		Object[] params = {userUpdate.getUsername(), userUpdate.getLastname(), userUpdate.getPassword(), userUpdate.getEmail(), id};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        
		return this.jdbcTemplate.update(sql, params, types);				
	}

}
