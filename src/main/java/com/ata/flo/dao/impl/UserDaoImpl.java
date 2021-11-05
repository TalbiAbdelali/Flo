package com.ata.flo.dao.impl;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ata.flo.dao.UserDao;
import com.ata.flo.model.User;

@Repository("UserPsqlTable")
public class UserDaoImpl implements UserDao{

	private final JdbcTemplate jdbcTemplate;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserDaoImpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
		this.jdbcTemplate = jdbcTemplate;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public int insertUser(String id, User user) throws Exception{
		
		try {
			final String sql = "insert into users(id, username, lastname, password, email, isenabled, roles, permissions) values(?,?,?,?,?,?,?,?)";
			
			String defaultPermissions = "user:read,article:read";
			String defaultRoles = "USER";
			//Object[] params = {UUID.randomUUID().toString(), user.getUsername(), user.getLastname(), passwordEncoder.encode(user.getPassword()), user.getEmail(), user.getActive() == 1, String.join( ",", user.getRoles()), String.join( ",", user.getPermissions())};
			Object[] params = {UUID.randomUUID().toString(), user.getUsername(), user.getLastname(), passwordEncoder.encode(user.getPassword()), user.getEmail(), "t", String.join( ",", defaultRoles), String.join( ",", defaultPermissions)};
	        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN, Types.VARCHAR, Types.VARCHAR};
			
	        return this.jdbcTemplate.update(sql, params, types);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<User> selectAllUsers() {
		final String sql ="SELECT * FROM  users";
 		return jdbcTemplate.query(sql, (resultSet, i) -> {
 			String userId = resultSet.getString("id");
 			String username = resultSet.getString("username");
 			String password = resultSet.getString("password");
 			String lastname = resultSet.getString("lastname");
 			String email = resultSet.getString("email");
 			boolean isEnabled = resultSet.getBoolean("isenabled");
 			int active = isEnabled ? 1 : 0;
 			List<String> roles = null;
			List<String> permissions = null;
 			if((resultSet.getString("roles") != null && !resultSet.getString("roles").isEmpty())
 					|| (resultSet.getString("permissions") != null && !resultSet.getString("permissions").isEmpty())) {
 				roles = Arrays.asList(resultSet.getString("roles").split(","));
 				permissions = Arrays.asList(resultSet.getString("permissions").split(","));
 			}
 			return new User(userId, username, lastname, password, email, active, roles, permissions);
 		});
	}

	@Override
	public Optional<User> selectUserById(String id) {
		final String sql ="SELECT * FROM users WHERE id = ?";
 		User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
 			String userId = resultSet.getString("id");
 			String username = resultSet.getString("username");
 			String password = resultSet.getString("password");
 			String lastname = resultSet.getString("lastname");
 			String email = resultSet.getString("email");
 			boolean isEnabled = resultSet.getBoolean("isenabled");
 			int active = isEnabled ? 1 : 0;
 			List<String> roles = null;
			List<String> permissions = null;
			if((resultSet.getString("roles") != null && !resultSet.getString("roles").isEmpty())
 					|| (resultSet.getString("permissions") != null && !resultSet.getString("permissions").isEmpty())) {
 				roles = Arrays.asList(resultSet.getString("roles").split(","));
 				permissions = Arrays.asList(resultSet.getString("permissions").split(","));
 			}
 			return new User(userId, username, lastname, password, email, active, roles, permissions);
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

	@Override
	public Optional<User> selectUserByUsername(String username) {
		final String sql ="SELECT * FROM users WHERE username = ?";
 		User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, (resultSet, i) -> {
 			String userId = resultSet.getString("id");
 			String password = resultSet.getString("password");
 			String lastname = resultSet.getString("lastname");
 			String email = resultSet.getString("email");
 			boolean isEnabled = resultSet.getBoolean("isenabled");
 			int active = isEnabled ? 1 : 0;
 			List<String> roles = null;
			List<String> permissions = null;
			if((resultSet.getString("roles") != null && !resultSet.getString("roles").isEmpty())
 					|| (resultSet.getString("permissions") != null && !resultSet.getString("permissions").isEmpty())) {
 				roles = Arrays.asList(resultSet.getString("roles").split(","));
 				permissions = Arrays.asList(resultSet.getString("permissions").split(","));
 			}
 			return new User(userId, username, lastname, password, email, active, roles, permissions);
 		});
 		return Optional.ofNullable(user);
	}

	@Override
	public boolean isEmailExiste(String email) {
		String sql = "SELECT count(*) FROM users WHERE email = ?";

	    int count = jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);

	    return count > 0;
	}

}
