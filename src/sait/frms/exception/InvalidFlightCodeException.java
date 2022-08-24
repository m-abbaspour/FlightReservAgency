package sait.frms.exception;

/**
 * This exception is thrown when the flight code specified is incorrect/ invalid.
 * @author Mahdiyeh
 *
 */
public class InvalidFlightCodeException extends Exception {
    private String invalidFlightCode;
	public InvalidFlightCodeException(String invalidFlightCode){
		super(invalidFlightCode + " is not a valid flight code!" );
		this.invalidFlightCode=invalidFlightCode;
	}
    public String getInvalidFlightCode(){
		return invalidFlightCode;
	}
}
