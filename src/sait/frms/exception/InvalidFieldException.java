package sait.frms.exception;

/**
 * This exception is thrown when the mandatory fields are empty
 * @author Mahdiyeh
 *
 */
public class InvalidFieldException extends Exception {
    private String invalidField;
	public InvalidFieldException(String invalidField){
		super(invalidField + " is empty null!" );
		this.invalidField=invalidField;
	}
    public String getInvalidField(){
		return invalidField;
	}
}
