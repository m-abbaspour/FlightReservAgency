package sait.frms.problemdomain;

import sait.frms.exception.InvalidFlightCodeException;

/**
 * 
 * @author Mahdiyeh
 *
 */
public class Flight {

	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;

	/**
	 * Creates a flight with default values
	 */
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a flight object providing the code, airlineName, from, to, weekday,
	 * time, seats and costPerSeat
	 * 
	 * @param code        code of the flight
	 * @param airlineName of the flight
	 * @param from        the
	 * @param to          the destination of the flight
	 * @param weekday     the day of departure
	 * @param time        the time of the departure
	 * @param seats       the number of seats on the flight
	 * @param costPerSeat the cost of each seat on the flight
	 */
	public Flight(String code, String airlineName, String from, String to, String weekday, String time, int seats,
			double costPerSeat) throws InvalidFlightCodeException {
		super();
		parseCode(code);
		this.code = code;
		this.airlineName = airlineName;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	/**
	 * Returns the code of the flight
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns the airlineName of the flight
	 * 
	 * @return the airlineName
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * Returns the from (origin) of the flight
	 * 
	 * @return the from (origin) of the flight 
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * Returns the to (destinations) of the flight
	 * 
	 * @return the to (destinations) of the flight
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Returns the weekday
	 * 
	 * @return the weekday
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * Returns the time
	 * 
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Returns the number of seats on the flight
	 * 
	 * @return the number of seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Returns the cost of each seat on the flight
	 * 
	 * @return costPerSeat 
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}

	/**
	 * Returns true if the flight is domestic and false if it isn't
	 * 
	 * @return isDomestic 
	 */
	public boolean isDomestic() {
		return  (from.substring(0,1).equals("Y") && to.substring(0,1).equals("Y"));
//		return  (from.charAt(0)=='Y' && to.charAt(0)=='Y');
	}
	
	/**
	 * parses the code to an Integer
	 * 
	 * @param flightCode
	 */
	public static void parseCode(String flightCode) throws InvalidFlightCodeException {
		//The flight code should have a LL-DDDD format (L for letter and D for digit)

		if(!flightCode.matches("[A-Z]{2}[-][0-9]{4}") ) {
	    	throw new InvalidFlightCodeException(flightCode);
	    }

	}

//	@Override
//	public String toString() {
//		return "Flight [code=" + code + ", airlineName=" + airlineName + ", from=" + from + ", to=" + to + ", weekday="
//				+ weekday + ", time=" + time + ", seats=" + seats + ", costPerSeat=" + costPerSeat + "]";
//	}
	@Override
	public String toString() {
		return code + ", From: " + from + ", To: " + to + ", Day:" + weekday
				+ ", Cost:" + costPerSeat ;
	}
	
	
}
