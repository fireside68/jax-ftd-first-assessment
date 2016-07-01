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
	private ClientHandler handler;
	private UserDAO userDAO;
	private FilesDAO filesDAO;
	private UserFilesDAO userFilesDAO;

	public Server() {
		super();

	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(667)) {

			while (true) {
				Socket clientSocket = serverSocket.accept();
				handler = new ClientHandler(clientSocket);
				this.userDAO = handler.getUserDAO();
				this.filesDAO = handler.getFilesDAO();
				this.userFilesDAO = handler.getUserFilesDAO();
				this.log.info("Client connected {}", clientSocket.getRemoteSocketAddress());
				
				
				this.executor.execute(handler);
			}
		} catch (IOException e) {

		}
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
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

	public ClientHandler getHandler() {
		return handler;
	}

	public void setHandler(ClientHandler handler) {
		this.handler = handler;
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