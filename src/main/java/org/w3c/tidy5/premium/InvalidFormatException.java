package org.w3c.tidy5.premium;

public class InvalidFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String f;
	private char c;
	public InvalidFormatException(String f,char c){
		this.f=f;
		this.c=c;
	}
	   public String getMessage() {
	        return f + "error start with "+c;
	    }
}
