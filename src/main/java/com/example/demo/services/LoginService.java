package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.example.demo.exceptions.LoginException;


@Component
public class LoginService {

	@Autowired
	private ConfigurableApplicationContext ctx;

	// right now, admin is the only user type but more user types can easily be added to this service.
	public UserFacade Login(String email, String password) throws LoginException{

			AdminFacade admin = ctx.getBean(AdminFacade.class);
			if (admin.login(email, password) == true) 
				return admin;
		
			else throw new LoginException();
			}

	public LoginService() {
	}

}
