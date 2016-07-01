package com.cooksys.ftd.assessment.filesharing.api;

import com.cooksys.ftd.assessment.filesharing.dao.UserFilesDAO;
import com.cooksys.ftd.assessment.filesharing.model.Files;
import com.cooksys.ftd.assessment.filesharing.model.User;
import com.cooksys.ftd.assessment.filesharing.model.UserFiles;

public class AddFile {
	
	private User user;
	private Files files;
	private UserFiles userFiles;
	private UserFilesDAO userFilesDAO;
	
	public AddFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddFile(User user, Files files, UserFiles userFiles) {
		this.user = user;
		this.files = files;
		this.userFiles = userFiles;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Files getFiles() {
		return files;
	}
	public void setFiles(Files files) {
		this.files = files;
	}
	public UserFiles getUserFiles() {
		return userFiles;
	}
	public void setUserFiles(UserFiles userFiles) {
		this.userFiles = userFiles;
	}
	public UserFilesDAO getUserFilesDAO() {
		return userFilesDAO;
	}
	public void setUserFilesDAO(UserFilesDAO userFilesDAO) {
		this.userFilesDAO = userFilesDAO;
	}
	
	
	
	
	
	
}
