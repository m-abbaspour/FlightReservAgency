package sait.frms.exception;

/**
 * The InvalidCitizenshipException is an exception that is thrown when 
 * the user does not enter a name when they are making a reservation.
 * 
 * @author Mahdiyeh
 *
 */
public class InvalidCitizenshipException extends Exception {

	public InvalidCitizenshipException(){
		super("Citizenship is empty!" );
	}

}
