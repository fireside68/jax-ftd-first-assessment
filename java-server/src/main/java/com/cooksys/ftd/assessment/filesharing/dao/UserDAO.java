package com.cooksys.ftd.assessment.filesharing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.cooksys.ftd.assessment.filesharing.model.User;

public class UserDAO extends AbstractDAO {
	
	private int userID;
	private String username;
	private String password;
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public User createUser(User user) throws SQLException {
		this.stmt = getConn().prepareStatement("insert into user (user_id, username, password) "
				+ "values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
		this.stmt.setInt(1, getUserID());
		this.stmt.setString(2, getUsername());
		this.stmt.setString(3, getPassword());
		this.stmt.executeUpdate();
		
		this.rs = stmt.getGeneratedKeys();
		while(rs.next()){
			user.setUserID(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
		}
		return user;
	}
	
	public Optional<User> getUserByUsername (String username) {
		User user = new User();
		try {
		this.stmt = getConn().prepareStatement("select from user (username) where username = '?'");
		stmt.setString(1, username);
		this.rs = stmt.executeQuery();
		while(rs.next()){
			user.setUserID(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
		}
		return Optional.ofNullable(user);
		} catch (SQLException e){
		return Optional.ofNullable(null);
		}
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PreparedStatement getStmt() {
		return stmt;
	}

	public void setStmt(PreparedStatement stmt) {
		this.stmt = stmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	

}
