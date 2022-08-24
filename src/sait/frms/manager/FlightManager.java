package sait.frms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import sait.frms.exception.InvalidFlightCodeException;
import sait.frms.problemdomain.Flight;

/**
 * The manager class of the Flights that has: 
 * FlightManager():			The constructor that calls the populateAirports() and populateFlights() 
 * methods getAirports():	Returns the arrayList containing the list of the airports 
 * getFlights():			Returns the arrayList containing the lit of flights 
 * findAirportByCode(String code) 	 Finds the airport using the code 
 * findFlightByCode(String code)	 Finds the flight using the provided code 
 * findFlights(String from, String to, String weekday) populateFlights() Finds flights using the from, to and weekday
 * populateAirports() 		Populates the airports arrayList
 * 
 * @author Mahdiyeh
 *
 */
public class FlightManager {

	public static final String WEEKDAY_ANY = "";
	public static final String WEEKDAY_SUNDAY = "Sunday";
	public static final String WEEKDAY_MONDAY = "Monday";
	public static final String WEEKDAY_TUESDAY = "Tuesday";
	public static final String WEEKDAY_WEDNESDAY = "Wednesday";
	public static final String WEEKDAY_THURSDAY = "Thursday";
	public static final String WEEKDAY_FRIDAY = "Friday";
	public static final String WEEKDAY_SATURDAY = "Saturday";
	private static final String FILE_AIRPORTS = "res/airports.csv";
	private static final String FILE_FLIGHTS = "res/flights.csv";
	private ArrayList<Flight> flights = new ArrayList<Flight>();
	private ArrayList<String> airports = new ArrayList<String>();

	/**
	 * Creates a FlightManager with the default values and populates the flights and
	 * airport arrayLists
	 */
	public FlightManager() {
		super();
		// populate the flights ArrayList
		populateFlights();
		// populate the airports ArrayList
		populateAirports();
	}

	/**
	 * Returns the airports of the flight
	 * 
	 * @return the airports
	 */
	public ArrayList<String> getAirports() {
		return airports;
	}

	/**
	 * Returns the flights arrayList
	 * 
	 * @return the flights
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * Finds the airport using the code
	 * 
	 * @param code the code of the airport
	 * @return the full name of the airport
	 */
	public String findAirportByCode(String code) {
		String a = "";
		for (String airport : airports) {
			String[] arrOfAirport = airport.split(",", 2);
			if (arrOfAirport[0].equals(code)) {
				return arrOfAirport[1];
			}
		}
		return a;
	}

	/**
	 * Finds the flight using the provided code
	 * 
	 * @param code the code of the flight
	 * @return the flight associated with the code
	 */
	public Flight findFlightByCode(String code) {
		// finds the flight in the flights arrayList using the code
		for (Flight flight : flights) {
			if (flight.getCode().equalsIgnoreCase(code)) {
				return flight;
			}
		}
		return null;
	}

	/**
	 * Finds flights using the from, to and weekday
	 * 
	 * @param from    the from of the flight
	 * @param to      the to of the flight
	 * @param weekday the weekday of the flight
	 * @return the flights that match the from, to and weekday
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> foundMatches = new ArrayList<Flight>();
		// loop through the flights arrayList, if they match the from, to and weekday
		for (Flight flight : flights) {
			if (flight.getFrom().equalsIgnoreCase(from)) {
				if (flight.getTo().equalsIgnoreCase(to)) {
					if (weekday.equalsIgnoreCase("any") || flight.getWeekday().equalsIgnoreCase(weekday)) {
						// adds them to the foundMatches arraylist
						foundMatches.add(flight);
					}
				}
			}
		}
		return foundMatches;
	}

	/**
	 * populates the flights arrayList
	 */
	private void populateFlights() {
		try {
			Scanner in = new Scanner(new File(FILE_FLIGHTS));
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] fields = line.split(",");
				// Code 0, airline name 1, Departing Airport Code 2, Arrival Airport Code 3,
				// Weekday 4, Time 5, Seats 6, Cost Per Seat 7
				try {
					Flight flight = new Flight(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
							Integer.parseInt(fields[6]), Double.parseDouble(fields[7]));
					flights.add(flight);
				} catch (InvalidFlightCodeException e) {
					System.out.println(
							"InvalidFlightCodeException occured with Flight Code : " + e.getInvalidFlightCode());
				}

			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * populates the airports arrayList
	 */
	private void populateAirports() {

		try {
			Scanner in = new Scanner(new File(FILE_AIRPORTS));
			while (in.hasNextLine()) {
				String line = in.nextLine();
				airports.add(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
