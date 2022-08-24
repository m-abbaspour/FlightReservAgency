package sait.frms.exception;

/**
 * This exception is thrown when there are no more seats left on a flight.
 * @author Mahdiyeh
 *
 */
public class NoMoreSeatsException extends Exception {

	public NoMoreSeatsException(String flightCode){
		super("There are no available seats left on the flight " + flightCode + " left!" );
	}
}
