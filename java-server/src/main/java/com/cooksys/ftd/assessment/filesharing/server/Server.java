package com.cooksys.ftd.assessment.filesharing.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assessment.filesharing.dao.FilesDAO;
import com.cooksys.ftd.assessment.filesharing.dao.UserDAO;
import com.cooksys.ftd.assessment.filesharing.dao.UserFilesDAO;

public class Server implements Runnable {

	private static final boolean alive = false;

	private Logger log = LoggerFactory.getLogger(Server.class);

	private ExecutorService executor;
	private ServerSocket serverSocket;
	private UserDAO userDAO;
	private FilesDAO filesDAO;
	private UserFilesDAO userFilesDAO;

	private int port;

	public Server() {
		super();

	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(this.port)) {
			this.log.info("Client connected");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				ClientHandler clientHandler = this.createClientHandler(clientSocket);
				this.executor.execute(clientHandler);
			}
		} catch (IOException e) {
			log.error("You have reached a number that has been disconnected.", e);
		}
	}
	
	public ClientHandler createClientHandler(Socket socket) throws IOException {
		ClientHandler handler = new ClientHandler();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		handler.setReader(reader);
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		handler.setUserDAO(this.userDAO);
		handler.setFilesDAO(this.filesDAO);
		handler.setUserFilesDAO(this.userFilesDAO);
		return handler;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
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

	public void setPort(int port) {
		this.port = port;;		
	}

}