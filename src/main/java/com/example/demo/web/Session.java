package com.example.demo.web;

import com.example.demo.services.UserFacade;

public class Session {

	private UserFacade facade;
	private long lastAccessed;
	private int numOfTries;
	
	
	public Session(UserFacade facade, long lastAccessed) {
		super();
		this.facade = facade;
		this.lastAccessed = lastAccessed;
	}


	public long getLastAccessed() {
		return lastAccessed;
	}
	
	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public int getNumOfTries() {
		return numOfTries;
	}

	public UserFacade getFacade() {
		return facade;
	}



	

}
