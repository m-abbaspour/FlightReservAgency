package sait.frms.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import sait.frms.exception.InvalidCitizenshipException;
import sait.frms.exception.InvalidFieldException;
import sait.frms.exception.NoMoreSeatsException;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * The Manager class of the Reservation.java
 * 
 * ReservationManager() the constructor
 * makeReservation(Flight flight, String name, String citizenship) throws NoMoreSeatsException, InvalidCitizenshipException, InvalidFieldException
 * findReservations(String code, String airline, String name)
 * findReservationByCode(String code)
 * getAvailableSeats(Flight flight) throws NoMoreSeatsException
 * generateReservationCode(Flight flight)
 * persist() Saves Reservation objects to a random access file (FILE_RESERVATIONS)
 * populateFromBinary() throws Exception
 * 
 * 
 * 
 * @author Mahdiyeh
 *
 */
public class ReservationManager {
	private static final String FILE_RESERVATIONS = "res/reservations.dat";
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

	public ReservationManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param flight flight object to make the reservation for
	 * @param name name of the traveler
	 * @param citizenship citizenship of the traveler
	 * @return the reservation object made 
	 * @throws NoMoreSeatsException if there are no more seats left on the flight
	 * @throws InvalidCitizenshipException if there is no citizenship specified for the traveler
	 * @throws InvalidFieldException if there is no name specified for the traveler
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws NoMoreSeatsException, InvalidCitizenshipException, InvalidFieldException {
//		(String code, String flightCode, String airline, String name, String citizenship, double cost,boolean active)
		if(citizenship == null || citizenship.equals("")) {
			throw new InvalidCitizenshipException();
		}
		if (flight==null || flight.getCode().equals(""))
			throw new InvalidFieldException("flight");
		if (name==null || name.equals(""))
			throw new InvalidFieldException("name");
		Reservation reservation = new Reservation(generateReservationCode(flight), flight.getCode(),
				flight.getAirlineName(), name, citizenship, flight.getCostPerSeat(), true);
		reservations.add(reservation);
		return reservation;
	}

	/**
	 * Gets the reservation(s) with the specifications of the code of the flight, 
	 * airline's name. and the name of the traveler.
	 * 
	 * @param code the code of the flight
	 * @param airline the airline that operates the flight
	 * @param name the name of the traveler
	 * @return the reservation found with the specification of the flight code, 
	 *			name of the airline that operates the flight, travelers name.
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		ArrayList<Reservation> foundMatches = new ArrayList<Reservation>();
		// loop through the reservations arrayList, if they match the code, airline and
		// name
		for (Reservation reservation : reservations) {
			if (code.length() == 0 || reservation.getCode().equalsIgnoreCase(code)) {
				if (airline.length() == 0 || reservation.getAirline().equalsIgnoreCase(airline)) {
					if (name.length() == 0 || reservation.getName().equalsIgnoreCase(name)) {
						// adds them to the foundMatches arrayList
						foundMatches.add(reservation);
					}
				}
			}
		}
		return foundMatches;
	}

	/**
	 * Finds the reservation using the code of the flight.
	 * 
	 * @param code code of the flight
	 * @return the reservation object with the same flight code
	 */
	public Reservation findReservationByCode(String code) {
		for (Reservation reservation : reservations) {
			if (reservation.getCode().equalsIgnoreCase(code)) {
				return reservation;
			}
		}
		Reservation r = new Reservation();
		return r;
	}

	/**
	 * Gets the number of available seats left on a flight
	 * 
	 * @param flight the flight object
	 * @return the number of seats available for the flight
	 * @throws NoMoreSeatsException 
	 */
	public int getAvailableSeats(Flight flight) throws NoMoreSeatsException {
		int seatsTaken = 0;
		for (Reservation reservation : reservations)
			if (reservation.getFlightCode().equalsIgnoreCase(flight.getCode()) && reservation.isActive()) {
				seatsTaken++;
			}
		 int seatAvailable=flight.getSeats() - seatsTaken;
		 if (seatAvailable==0){
				throw new NoMoreSeatsException(flight.getCode());
			}
		return seatAvailable;
	}

	/**
	 * Generates a reservation code
	 * 
	 * @param flight the flight object
	 * @return the reservation code
	 */
	private String generateReservationCode(Flight flight) {
		Random rnd = new Random();
		int number = rnd.nextInt(9999);
		return (flight.isDomestic() ? "D" : "I") + String.format("%04d", number);
	}

	/**
	 * Saves Reservation objects to a random access file (FILE_RESERVATIONS)
	 * 
	 */
	public void persist() {
		/*
		FileOutputStream output= null;
		DataOutputStream doutput = null;

		output= new FileOutputStream(FILE_RESERVATIONS);
		doutput= new DataOutputStream(output);
			// for each  string in string buffer
			for(Reservation reservation:reservations) {
				doutput.writeUTF(reservation.getCode());
				doutput.writeUTF(reservation.getFlightCode());
				doutput.writeUTF(reservation.getAirline());
				doutput.writeUTF(reservation.getName());
				doutput.writeUTF(reservation.getCitizenship());
				doutput.writeDouble(reservation.getCost());
				doutput.writeBoolean(reservation.isActive());
			}
			// force data to the underlying file output stream
			doutput.flush();
			doutput.close();
			output.close();
			*/

		try {
			RandomAccessFile raf = new RandomAccessFile(FILE_RESERVATIONS, "rw");

			raf.setLength(0);

			for (Reservation reservation : this.reservations) {
				raf.writeUTF(reservation.getCode());
				raf.writeUTF(reservation.getFlightCode());
				raf.writeUTF(String.format("%-20s", reservation.getAirline()));
				raf.writeUTF(String.format("%-75s", reservation.getName()));
				raf.writeUTF(String.format("%-75s", reservation.getCitizenship()));
				raf.writeDouble(reservation.getCost());
				raf.writeBoolean(reservation.isActive());
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * populates the reservations arrayList with Reservation objects read from the
	 * FILE_RESERVATIONS file
	 * 
	 * @throws Exception
	 */
	public void populateFromBinary() throws Exception {
		DataInputStream dinput = null;

		InputStream input = null;
		File file = new File(FILE_RESERVATIONS);
		if (!file.exists()) {
			return;
		}
		input = new FileInputStream(FILE_RESERVATIONS);
		dinput = new DataInputStream(input);
		reservations.clear();
		while (dinput.available() > 0) {
//			String code, String flightCode, String airline, String name, String citizenship,
//			double cost,boolean active
			Reservation reservation = new Reservation(dinput.readUTF().trim(), dinput.readUTF().trim(), dinput.readUTF().trim(),
					dinput.readUTF().trim(), dinput.readUTF().trim(), dinput.readDouble(), dinput.readBoolean());
			reservations.add(reservation);
		}
		dinput.close();
		input.close();
	}

}
