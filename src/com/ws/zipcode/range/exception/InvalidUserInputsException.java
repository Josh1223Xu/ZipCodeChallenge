package com.ws.zipcode.range.exception;
/**
 * Custom exception class created for invalid user inputs
 * @author Jia(Josh) Xu
 *
 */
public class InvalidUserInputsException extends Exception {
	
	public InvalidUserInputsException(String errorMessage){
		super(errorMessage);
	}

}
