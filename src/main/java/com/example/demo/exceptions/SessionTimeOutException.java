package com.example.demo.exceptions;

public class SessionTimeOutException extends Exception {

	public SessionTimeOutException(){
		super("Session Expired !");
	}
}
