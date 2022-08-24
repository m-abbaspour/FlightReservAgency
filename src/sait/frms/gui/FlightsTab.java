package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.exception.InvalidCitizenshipException;
import sait.frms.exception.InvalidFieldException;
import sait.frms.exception.NoMoreSeatsException;
import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	private JComboBox tosBox;
	private JComboBox fromsBox;
	private JComboBox daysBox;
	private JButton findFlightsButton;
	private JTextField flightField;
	private JTextField airlineField;
	private JTextField dayField;
	private JTextField timeField;
	private JTextField costField;
	private JTextField nameField;
	private JTextField citizenshipField;
	private JButton reserveButton;

	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);

		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
	}


	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createEastPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));// top left bottom right
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;
		panel.add(centerPanel, BorderLayout.CENTER);

		JLabel title = new JLabel("Reserve");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 25));

//		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 20, 0);
		northPanel.add(title, constraints);
//		centerPanel.add(title);

		// flight
		JLabel flightJLabel = new JLabel("Flight:");
		flightJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 0, 0);
		centerPanel.add(flightJLabel, constraints);
		flightField = new JTextField(10);
		flightField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 2;
		centerPanel.add(flightField, constraints);

		// airline
		JLabel airlineJLabel = new JLabel("Airline:");
		airlineJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		centerPanel.add(airlineJLabel, constraints);
		airlineField = new JTextField(10);
		airlineField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 3;
		centerPanel.add(airlineField, constraints);

		// day
		JLabel dayJLabel = new JLabel("Day:");
		dayJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 4;
		centerPanel.add(dayJLabel, constraints);
		dayField = new JTextField(10);
		dayField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 4;
		centerPanel.add(dayField, constraints);

		// time
		JLabel timeJLabel = new JLabel("Time:");
		timeJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 5;
		centerPanel.add(timeJLabel, constraints);
		timeField = new JTextField(10);
		timeField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 5;
		centerPanel.add(timeField, constraints);

		// Cost
		JLabel costJLabel = new JLabel("Cost:");
		costJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 6;
		centerPanel.add(costJLabel, constraints);
		costField = new JTextField(10);
		costField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 6;
		centerPanel.add(costField, constraints);

		// Name
		JLabel nameJLabel = new JLabel("Name:");
		nameJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 7;
		centerPanel.add(nameJLabel, constraints);
		nameField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 7;
		centerPanel.add(nameField, constraints);

		// CitizenShip
		JLabel citizenshipJLabel = new JLabel("Citizenship:");
		citizenshipJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 8;
		centerPanel.add(citizenshipJLabel, constraints);
		citizenshipField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 8;
		centerPanel.add(citizenshipField, constraints);

		// Reserve Button
		reserveButton = new JButton("Reserve");
		reserveButton.addActionListener(new flightsTabButtonActionListener());
		citizenshipJLabel.setHorizontalAlignment(JButton.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(20, 0, 0, 0);
		constraints.anchor = GridBagConstraints.PAGE_END; // bottom of space

		centerPanel.add(reserveButton, constraints);

		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createSouthPanel() {
		String[] airports = { "YYC", "YEG", "YUL", "YOW", "YYZ", "YVR", "YWG", "ATL", "PEK", "DXB", "HKG", "LHR", "HND",
				"ORD", "PVG", "CDG", "AMS", "DEL", "FRA", "DFW" };
		String[] days = { "Any", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 15));

		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		panel.add(centerPanel, BorderLayout.CENTER);

		JLabel title = new JLabel("Flight Finder");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 25));
		northPanel.add(title);

		JLabel fromLabel = new JLabel("From:");
		fromLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.01;
		centerPanel.add(fromLabel, constraints);

		fromsBox = new JComboBox(airports);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 5;
		centerPanel.add(fromsBox, constraints);

		JLabel toLabel = new JLabel("To:");
		toLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 0.01;
		centerPanel.add(toLabel, constraints);

		tosBox = new JComboBox(airports);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 5;
		centerPanel.add(tosBox, constraints);

		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 0.01;
		centerPanel.add(dayLabel, constraints);

		daysBox = new JComboBox(days);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weightx = 5;
		centerPanel.add(daysBox, constraints);

		findFlightsButton = new JButton("Find Flights");
		findFlightsButton.addActionListener(new flightsTabButtonActionListener());
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.gridy = 4;
		constraints.weightx = 5;
		centerPanel.add(findFlightsButton, constraints);
		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 50, 10));

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		panel.add(scrollPane);

		return panel;
	}

	/**
	 * This class is called when the user wants to search for a flight
	 * 
	 * @author Mahdiyeh
	 *
	 */
	private class flightsTabButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reserveButton) {
				if (flightsList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(panel, "ERROR: NO flight selectec!");
					return;
				}
				if (nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(panel, "ERROR: NO name specified!");
					return;
				}

				Flight flight = flightsModel.getElementAt(flightsList.getSelectedIndex());

				Reservation reservation;
				try {
					reservation = reservationManager.makeReservation(flight, nameField.getText(),
							citizenshipField.getText());
				} catch (NoMoreSeatsException e1) {
					JOptionPane.showMessageDialog(panel, "ERROR: this flight hasnot available seat!");
					return;
				} catch (InvalidCitizenshipException e2) {
					JOptionPane.showMessageDialog(panel, "ERROR: NO citizenship specified!");
					return;
				} catch (InvalidFieldException ex) {
					JOptionPane.showMessageDialog(panel, "ERROR: " + ex.getInvalidField() + " is not specified!");
					return;
				}
				JOptionPane.showMessageDialog(panel, "Reservation done code: " + reservation.getCode());

				reservationManager.persist();

				System.out.println("JButton reserveButton");
			} else if (e.getSource() == findFlightsButton) {
				System.out.println("JButton findFlightsButton");
				String from = fromsBox.getSelectedItem().toString();
				String to = tosBox.getSelectedItem().toString();
				String weekday = daysBox.getSelectedItem().toString();
				if (from.equals(to)) {
					JOptionPane.showMessageDialog(panel, "ERROR: form and to cannot be euals");
					return;
				}
				ArrayList<Flight> allFlight = flightManager.findFlights(from, to, weekday);
				if (allFlight.size() == 0) {
					JOptionPane.showMessageDialog(panel, "ERROR: no flight found");
					return;
				}
				if (!flightsModel.isEmpty())
					flightsModel.removeAllElements();
				for (Flight flight : allFlight) {
					flightsModel.addElement(flight);
				}
			}

		}
	}

	/**
	 * This class displays the flights specifications in the Reserve section of the
	 * of the flights tab and it is called when the user selects a flight from the
	 * text box. 
	 * 
	 * @author Mahdiyeh
	 *
	 */
	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			{
				if (flightsList.getSelectedIndex() == -1) {
					return;
				}
				Flight flight = flightsModel.getElementAt(flightsList.getSelectedIndex());
				flightField.setText(flight.getCode());
				airlineField.setText(flight.getAirlineName());
				dayField.setText(flight.getWeekday());
				timeField.setText(flight.getTime());
				costField.setText(String.valueOf(flight.getCostPerSeat()));
			}
		}

	}
}