package com.example.demo.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DB.ProductRepo;
import com.example.demo.exceptions.LoginException;

public abstract class UserFacade {
	
	@Autowired
	protected ProductRepo stock;
	
	
	// All Facades will inherit login method to check if user exists in DB according to email and password, 
	// and return true if exists or false if doesn't exist
	public abstract boolean login (String email,String password) throws LoginException, SQLException ; 

}
