package com.cooksys.ftd.assessment.filesharing.dao;

import java.sql.Connection;

public class AbstractDAO {

		private Connection conn;

		public Connection getConn() {
			return conn;
		}

		public void setConn(Connection conn) {
			this.conn = conn;
		}
}
