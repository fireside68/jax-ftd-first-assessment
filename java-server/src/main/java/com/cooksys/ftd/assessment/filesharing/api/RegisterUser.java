package com.cooksys.ftd.assessment.filesharing.api;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assessment.filesharing.model.User;

public class RegisterUser {
	
	String username;
	String password;
	private static Logger log = LoggerFactory.getLogger(RegisterUser.class);
	
	public RegisterUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Map<String, String> addUser (String username, String password){
		Map<String, String> users = new HashMap<>();
		
		
		return users;
		
		
	}
	
	public User unmarshall (String strang) throws  JAXBException{
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("eclipse.persistence.jaxb", "application/json");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
	    jaxbUnmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    StringReader json = new StringReader(strang);
	    User u = (User) jaxbUnmarshaller.unmarshal(json);
	    log.info("{}", u);
	    return u;
	}

}
