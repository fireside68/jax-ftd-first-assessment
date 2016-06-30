package com.cooksys.ftd.assessment.filesharing.server;

import java.io.BufferedReader;
import java.io.PrintWriter;

import com.cooksys.ftd.assessment.filesharing.dao.FilesDAO;
import com.cooksys.ftd.assessment.filesharing.dao.UserDAO;
import com.cooksys.ftd.assessment.filesharing.dao.UserFilesDAO;

public class ClientHandler implements Runnable{
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	private UserDAO userDAO;
	private FilesDAO filesDAO;
	private UserFilesDAO userFilesDAO;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public BufferedReader getReader() {
		return reader;
	}
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	public PrintWriter getWriter() {
		return writer;
	}
	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public FilesDAO getFilesDAO() {
		return filesDAO;
	}
	public void setFilesDAO(FilesDAO filesDAO) {
		this.filesDAO = filesDAO;
	}
	public UserFilesDAO getUserFilesDAO() {
		return userFilesDAO;
	}
	public void setUserFilesDAO(UserFilesDAO userFilesDAO) {
		this.userFilesDAO = userFilesDAO;
	}
	
	
}
