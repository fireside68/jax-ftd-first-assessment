package com.cooksys.ftd.assessment.filesharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assessment.filesharing.dao.FilesDAO;
import com.cooksys.ftd.assessment.filesharing.dao.UserDAO;
import com.cooksys.ftd.assessment.filesharing.dao.UserFilesDAO;
import com.cooksys.ftd.assessment.filesharing.server.Server;


public class Main {
	private static Logger log = LoggerFactory.getLogger(Main.class);

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/assessment_cj";
	private static String username = "root";
	private static String password = "bondstone";

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName(driver); // register jdbc driver class
		ExecutorService executor = Executors.newCachedThreadPool(); // initialize
																	// thread
																	// pool

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			Server server = new Server(); // init server

			server.setExecutor(executor);

			UserDAO userDAO = new UserDAO();
			userDAO.setConn(conn);
			server.setUserDAO(userDAO);

			UserFilesDAO userFilesDAO = new UserFilesDAO();
			userFilesDAO.setConn(conn);
			server.setUserFilesDAO(userFilesDAO);

			FilesDAO filesDAO = new FilesDAO();
			filesDAO.setConn(conn);
			server.setFilesDAO(filesDAO);

			Future<?> serverFuture = executor.submit(server); // start server
																// (asynchronously)

			serverFuture.get(); // blocks until server's #run() method is done
								// (aka the server shuts down)

		} catch (SQLException | InterruptedException | ExecutionException e) {
			log.error("An error occurred during server startup. Shutting down after error log.", e);
		} finally {
			executor.shutdown(); // shutdown thread pool (see inside of try
									// block for blocking call)
		}
	}
}

