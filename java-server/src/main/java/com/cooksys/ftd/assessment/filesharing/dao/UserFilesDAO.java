package com.cooksys.ftd.assessment.filesharing.dao;

@XmlRootElement
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.cooksys.ftd.assessment.filesharing.model.Files;
import com.cooksys.ftd.assessment.filesharing.model.User;

public class UserFilesDAO extends AbstractDAO {
	
	private int userID;
	private int fileID;
	private User user;
	private Files file;
	private List<Files> files;
	private Map<User, List<Files>> storedFiles = new TreeMap<User, List<Files>>();
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public List<Files> getFiles(User user) throws SQLException {
		List<Files> files = new ArrayList<>();
		this.stmt = getConn().prepareStatement("select from user_file (file_id) where "
				+ "user_id = '?'", PreparedStatement.RETURN_GENERATED_KEYS);
		this.stmt.setInt(1, user.getUserID());
		stmt.executeQuery();
		while(rs.next()) {
			this.file.setFileID(rs.getInt("file_id"));
			this.file.setFilepath(rs.getString("file_path"));
			this.file.setFileContents(rs.getBytes("file_contents"));
			files.add(this.file);
		}
		return files;
	}
	
	public Map<User, List<Files>> addFiles(User user, Files file) {
		
		return null;
	}
	
	@XmlAttribute
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	@XmlAttribute
	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	
	@XmlElement
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@XmlElement
	public Files getFile() {
		return file;
	}

	public void setFile(Files file) {
		this.file = file;
	}
	
	
	@XmlElement
	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
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
	
	

}
