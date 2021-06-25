package com.example.demo.exceptions;

public class AlreadyExistsException extends Exception {

	public AlreadyExistsException() {
		super("Inventory Code/item title exists already ! give a new code/title");
	}
}
