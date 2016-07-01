package com.cooksys.ftd.assessment.filesharing.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowPassword {
	
	private String password;
	private PreparedStatement stmt;
	private Connection conn;
	
	public String getUserByPassword(String password) throws SQLException {
		
		String user;
		this.stmt = this.conn.prepareStatement("select from user (username) where password = '?'");
		stmt.setString(1, password);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			user = (rs.getString("username"));
			return user;
		}
		
		return "USER NOT FOUND";
	}
	
//	try {    
//        File file = new File("employee.xml");    
//        JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);    
//     
//        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();    
//        Employee e=(Employee) jaxbUnmarshaller.unmarshal(file);    
//        System.out.println(e.getId()+" "+e.getName()+" "+e.getSalary());  
//          
//      } catch (JAXBException e) {e.printStackTrace(); }    
//     
//}  

}
