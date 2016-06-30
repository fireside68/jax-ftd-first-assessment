package com.cooksys.ftd.assessment.filesharing.model;

public class UserFiles {
	
	private int userID;
	private int fileID;
	public UserFiles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserFiles(int userID, int fileID) {
		this.userID = userID;
		this.fileID = fileID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fileID;
		result = prime * result + userID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFiles other = (UserFiles) obj;
		if (fileID != other.fileID)
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserFiles [userID=" + userID + ", fileID=" + fileID + "]";
	}
	
	
}
