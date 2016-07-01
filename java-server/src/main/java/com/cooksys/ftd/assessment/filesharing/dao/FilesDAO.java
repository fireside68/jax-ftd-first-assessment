package com.cooksys.ftd.assessment.filesharing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cooksys.ftd.assessment.filesharing.model.Files;

@XmlRootElement (name = "Files")
public class FilesDAO extends AbstractDAO {

	private int fileID;
	private String filepath;
	private byte[] fileContents;
	private Files files;
	private List<Files> filesList;

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public Files createFile(Files file) throws SQLException {
		this.stmt = getConn().prepareStatement(
				"insert into file (file_id, file_path, file_contents) values (?, ?, ?) " + "values (?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);
		this.stmt.setInt(1, getFileID());
		this.stmt.setString(2, getFilepath());
		this.stmt.setBytes(2, getFileContents());
		this.stmt.executeUpdate();

		this.rs = stmt.getGeneratedKeys();
		while (rs.next()) {
			file.setFileID(rs.getInt("file_id"));
			file.setFilepath(rs.getString("file_path"));
			file.setFileContents(rs.getBytes("file_contents"));
		}
		return file;
	}

	public Optional<Files> getFileID(int fileID) {
		this.files = new Files();
		try {
		this.stmt = getConn().prepareStatement("select from files (file_id) where file_id = '?'");
		stmt.setInt(1, getFileID());
		this.rs = stmt.executeQuery();
		while(rs.next()){
			files.setFileID(rs.getInt(1));
			files.setFilepath(rs.getString(2));
			files.setFileContents(fileContents);
		} 
		return Optional.ofNullable(files);
		}catch (SQLException e){
		return Optional.ofNullable(null);
		}
	}
	
	public String geFileByPath(String filePath) {
		this.files = new Files();
		try {
		this.stmt = getConn().prepareStatement("select from files (file_path) where file_path = '?'");
		stmt.setString(1, getFilepath());
		this.rs = stmt.executeQuery();
		rs.next();
		return rs.getString("file_path");
		}catch (SQLException e){
		return "FILE NOT FOUND";
		}
	}
	
	public byte[] getFileContentsbyFileID() {
		this.files = new Files();
		try {
		this.stmt = getConn().prepareStatement("select from files (file_id) where file_id = '?'");
		stmt.setInt(1, getFileID());
		this.rs = stmt.executeQuery();
		rs.next();
		byte[] arr = rs.getBytes("file_contents");
		return arr;
		}catch (SQLException e){
			return null;
		}
		
	}
	
	@XmlAttribute (name = "file_id")
	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	@XmlElement (name = "file_path")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@XmlElement (name = "Files")
	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}
	@XmlElement (name = "Files_List")
	public List<Files> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<Files> filesList) {
		this.filesList = filesList;
	}
	@XmlElement (name = "file_contents")
	public byte[] getFileContents() {
		return fileContents;
	}

	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
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
