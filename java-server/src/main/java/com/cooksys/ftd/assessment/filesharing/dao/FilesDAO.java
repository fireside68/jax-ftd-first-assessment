package com.cooksys.ftd.assessment.filesharing.dao;

import java.sql.Blob;
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

@XmlRootElement
public class FilesDAO extends AbstractDAO {

	private int fileID;
	private String filepath;
	private byte[] fileContents;
	private Blob blob;
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
			file.setFileID(rs.getInt(1));
			file.setFilepath(rs.getString(2));
			file.setFileContents(rs.getBytes(3));
		}
		return file;
	}

	public Optional<Files> getFilesByID(int fileID) {
		this.files = new Files();
		try {
		this.stmt = getConn().prepareStatement("select from files file_id where file_id = '?'");
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
	
	@XmlAttribute
	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	@XmlElement
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@XmlElement
	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}
	@XmlElement
	public List<Files> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<Files> filesList) {
		this.filesList = filesList;
	}
	@XmlElement
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
