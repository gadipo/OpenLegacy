package com.example.demo.exceptions;

public class NoSessionFoundException extends Exception {

	public NoSessionFoundException(){
		super("You have to login hombre !");
	}
}
