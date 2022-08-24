package sait.frms.problemdomain;

/**
 * 
 * @author Mahdiyeh
 *
 */
public class Reservation {

	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;

	/**
	 * Creates a reservation with the default values
	 */
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a Reservation object providing the code, flightCode, airline, name,
	 * citizenship, cost and active
	 * 
	 * @param code        The reservation code will be generated when a reservation is created. 
	 * @param flightCode  The flight code the reservation is for.
	 * @param airline     The airline who owns and operates the flight
	 * @param name        The name of the traveler
	 * @param citizenship The citizenship of the traveler
	 * @param cost        The cost of the reservation.
	 * @param active      the status (activeness) of the reservation
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost,
			boolean active) {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}


	/**
	 * Returns the code of the reservation
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns the flightCode of the reservation
	 * 
	 * @return the flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * Returns the airline of the reservation
	 * 
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Returns the name of the person who made the reservation
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the citizenship of the person who made the reservation
	 * 
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * Returns the cost of the reservation
	 * 
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Returns whether the reservation is active
	 * 
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the name to the person who made the reservation
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the citizenship to the person who made the reservation
	 * 
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * Sets the status of the reservation, whether it is active or not
	 * 
	 * @param active the status (active)
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/*
	@Override
	public String toString() {
		return "Reservation [code=" + code + ", flightCode=" + flightCode + ", airline=" + airline + ", name=" + name
				+ ", citizenship=" + citizenship + ", cost=" + cost + ", active=" + active + "]";
	}
  
	 */
	
	@Override
	public String toString() {
		return  code;
	}

}
