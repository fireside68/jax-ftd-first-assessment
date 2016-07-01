package com.cooksys.ftd.assessment.filesharing.server;


import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assessment.filesharing.api.RegisterUser;
import com.cooksys.ftd.assessment.filesharing.dao.FilesDAO;
import com.cooksys.ftd.assessment.filesharing.dao.UserDAO;
import com.cooksys.ftd.assessment.filesharing.dao.UserFilesDAO;
import com.cooksys.ftd.assessment.filesharing.model.User;

public class ClientHandler implements Runnable, Closeable {
	
	private static Logger log = LoggerFactory.getLogger(ClientHandler.class);

	private BufferedReader reader;
	private PrintWriter writer;
	private Socket client;
	private Server server;
	private ServerSocket serverSocket;
	private UserDAO userDAO;
	private UserFilesDAO userFilesDAO;
	private FilesDAO filesDAO;
	private String username;
	private RegisterUser registerUser;
	
	public ClientHandler() {
		super();
	}
	
	public ClientHandler(Socket client) throws IOException {
		this.client = client;
		this.writer = new PrintWriter(client.getOutputStream(), true);
		this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}
	

	@Override
	public void run() {
		try {
			String connected = this.reader.readLine();
			log.info("Received input: {}", connected);
			User temp = registerUser.unmarshall(connected);
			log.info("{}", temp);

			
		} catch (IOException | JAXBException e) {
			log.error("Stream reading error...", e);
		}

	}
	
	@Override
	public void close() throws IOException {
		this.client.close();		
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

	public UserFilesDAO getUserFilesDAO() {
		return userFilesDAO;
	}

	public void setUserFilesDAO(UserFilesDAO userFilesDAO) {
		this.userFilesDAO = userFilesDAO;
	}

	public FilesDAO getFilesDAO() {
		return filesDAO;
	}

	public void setFilesDAO(FilesDAO filesDAO) {
		this.filesDAO = filesDAO;
	}

	

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}