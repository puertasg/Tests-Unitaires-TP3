package dev.exception;

public class CalculException extends RuntimeException{
	
	public CalculException()
	{
		
	}
	
	public CalculException(String msg)
	{
		super(msg);
	}
}
