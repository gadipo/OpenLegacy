package com.example.demo.exceptions;

public class SupplyException extends Exception {
	
	public SupplyException() {
		super("Sorry ! supply is out !");
	}
	
	public SupplyException(int existingAmount) {
		super("Sorry ! there's only "+existingAmount+". please change withdraw amount accordingly.");
	}

}
