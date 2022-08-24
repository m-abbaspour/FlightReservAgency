package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sait.frms.exception.InvalidFlightCodeException;
import sait.frms.exception.NoMoreSeatsException;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	
	/**
	 * List of Reservations.
	 */
	private JList<Reservation> reservationsList;
	
	private DefaultListModel<Reservation> reservationsModel;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	//Components 
	private JLabel titleReserve;
	private JTextField codeReserveField;
	private JLabel codeReserveJLabel;
	
	private JTextField flightField;
	private JLabel flightLabel;
	
	private JTextField airlineField;
	private JLabel airlineLabel;
	
	private JTextField costField;
	private JLabel costLabel;

	private JTextField nameField;
	private JLabel nameLabel;

	
	private JTextField citizenshipField;
	private JLabel citizenshipLabel;

	private JComboBox statusBox;
	private JLabel statusLabel;

	private JButton updateButton;
	
	private JLabel titleSearch;

	private JTextField codeSearchField;
	private JLabel codeSearchLabel;

	private JTextField airlineSearchField;
	private JLabel airlineSearchLabel;

	private JTextField nameSearchField;
	private JLabel nameSearchLabel;

	private JButton findReservationsButton;


	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
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
	
	private JPanel createEastPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,  10, 0));// top left bottom right
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		panel.add(centerPanel, BorderLayout.CENTER);
		
		titleReserve = new JLabel("Reserve");
		titleReserve.setHorizontalAlignment(JLabel.CENTER);
		titleReserve.setFont(new Font("serif", Font.PLAIN, 25));
//		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0,0,20,0);
		northPanel.add(titleReserve, constraints);
//		centerPanel.add(title);
		
		//code
		codeReserveJLabel = new JLabel("Code:"); 
		codeReserveJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(0,0,0,0);
		centerPanel.add(codeReserveJLabel, constraints);
		codeReserveField =  new JTextField(10);
		codeReserveField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 2;
		centerPanel.add(codeReserveField, constraints);
		
		//Flight
		flightLabel = new JLabel("Flight:");
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		centerPanel.add(flightLabel, constraints);
		flightField =  new JTextField(10);
		flightField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 3;
		centerPanel.add(flightField, constraints);
		
		//airline
		airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 4;
		centerPanel.add(airlineLabel, constraints);		
		airlineField =  new JTextField(10);
		airlineField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 4;
		centerPanel.add(airlineField, constraints);
		
		//Cost
		costLabel = new JLabel("Cost:");
		costLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 5;
		centerPanel.add(costLabel, constraints);
		costField =  new JTextField(10);
		costField.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 5;
		centerPanel.add(costField, constraints);

		
		//Name
		nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 7;
		centerPanel.add(nameLabel, constraints);
		nameField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 7;
		centerPanel.add(nameField, constraints);
		
		//CitizenShip
		citizenshipLabel = new JLabel("Citizenship:");
		citizenshipLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 8;
		centerPanel.add(citizenshipLabel, constraints);
		citizenshipField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 8;
		centerPanel.add(citizenshipField, constraints);
		
		
		//Status
		statusLabel = new JLabel("Status:");
		statusLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.weightx = 0.01;
		centerPanel.add(statusLabel, constraints);
		
		String[] statuseStrings = {"Active" , "InActive"};
		statusBox = new JComboBox(statuseStrings);
		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.weightx = 5;
		centerPanel.add(statusBox, constraints);
		
		//Reserve Button
		updateButton = new JButton("Update");
		updateButton.addActionListener(new ReservationsTabButtonActionListener());
//		citizenshipJLabel.setHorizontalAlignment(JButton.RIGHT); ??
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth =2;
		constraints.insets = new Insets(20,0,0,0);
		constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space  
		centerPanel.add(updateButton, constraints);	
		
		return panel;
	}


	private JPanel createSouthPanel() {
		// TODO Auto-generated method stub
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 15));
		
		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		panel.add(centerPanel, BorderLayout.CENTER);
		
		//Search title
		titleSearch = new JLabel("Search");
		titleSearch.setHorizontalAlignment(JLabel.CENTER);
		titleSearch.setFont(new Font("serif", Font.PLAIN, 25));
		northPanel.add(titleSearch);	
		
		//code
		codeSearchLabel = new JLabel("Code:");
		codeSearchLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.01;
		centerPanel.add(codeSearchLabel, constraints);
		
		codeSearchField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 5;
		centerPanel.add(codeSearchField, constraints);

		//airline
		airlineSearchLabel = new JLabel("Airline:");
		airlineSearchLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 0.01;
		centerPanel.add(airlineSearchLabel, constraints);
		
		airlineSearchField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 5;
		centerPanel.add(airlineSearchField, constraints);

		//Name
		nameSearchLabel = new JLabel("Name:");
		nameSearchLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 0.01;
		centerPanel.add(nameSearchLabel, constraints);
		
		nameSearchField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weightx = 5;
		centerPanel.add(nameSearchField, constraints);
		
		//find reservation button
		findReservationsButton = new JButton("Find Reservations");
		findReservationsButton.addActionListener(new ReservationsTabButtonActionListener());	
		constraints.gridx = 0;
		constraints.gridwidth =2;
		constraints.gridy = 4;
		constraints.weightx = 5;
		centerPanel.add(findReservationsButton, constraints);
		
		return panel;
	}
	

	private JPanel createCenterPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
//		panel.setLayout(new GridBagLayout());
//		panel.setLayout(new FlowLayout());
		//												top, left bottom right
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10,  50, 10));
		reservationsModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationsModel);
		
		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scalable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		
		return panel;	}

	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel()
	{
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}
	/**
	 * Updates te reservstion
	 * @author Mahdiyeh
	 *
	 */
	private class ReservationsTabButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//           update button clicked
			if (e.getSource() == updateButton) {
				if (reservationsList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(panel,"ERROR: NO reservation selected!");
					return;
				}
				Reservation reservation = reservationsModel.getElementAt(reservationsList.getSelectedIndex());
				boolean status=statusBox.getSelectedItem().toString().equalsIgnoreCase("Active");
				String citizenShip=citizenshipField.getText().trim();
				String name=nameField.getText().trim();
				if (name.equals("")){
					JOptionPane.showMessageDialog(panel,"ERROR: NO name specified!");
					return;
				}
				if (citizenShip.equals("")){
					JOptionPane.showMessageDialog(panel,"ERROR: NO citizenship specified!");
					return;
				}

				if (name.equals(reservation.getName()) && citizenShip.equals(reservation.getCitizenship()) && status==reservation.isActive()){
					JOptionPane.showMessageDialog(panel,"ERROR: Nothing chenged!");
					return;
				}
				if ( status && !reservation.isActive()){
					try {
						Flight flight=new Flight(reservation.getFlightCode(), "", "", "", "", "", 0,0);
						reservationManager.getAvailableSeats(flight);
					} catch (NoMoreSeatsException | InvalidFlightCodeException e2) {
						JOptionPane.showMessageDialog(panel,"ERROR: No seat avalable!");
						return;						
					}
				}
				reservation.setName(name);
				reservation.setCitizenship(citizenShip);
				reservation.setActive(status);
				JOptionPane.showMessageDialog(panel,"Reservation updated. Your code is: "+reservation.getCode());
				
				reservationManager.persist();
				
			}
//    find reservation buttom clicked
			else if (e.getSource() == findReservationsButton){
				String code=codeSearchField.getText().trim();
				String airLine=airlineSearchField.getText().trim();
				String name=nameSearchField.getText().trim();
				if(code.equals("") && airLine.equals("") && name.equals("")) {
				JOptionPane.showMessageDialog(panel,"ERROR: Please select any criteria!");
				return;
				}
				ArrayList<Reservation> reservations=reservationManager.findReservations(code,airLine,name);
				if (reservations.size()==0) {
					JOptionPane.showMessageDialog(panel, "ERROR: no reservation found");
					return;
				}
				if (!reservationsModel.isEmpty())
					reservationsModel.removeAllElements();;
				for (Reservation reservation:reservations){
					reservationsModel.addElement(reservation);
				}
			}
		}

	}
	/**
	 * Called when user selects an item in the JList.
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
				if (reservationsList.getSelectedIndex()==-1)
					return;
				Reservation reservation = reservationsModel.getElementAt(reservationsList.getSelectedIndex());
				codeReserveField.setText(reservation.getCode());
				flightField.setText(reservation.getFlightCode());
				if (reservation.isActive())
					statusBox.setSelectedItem("Active");
				else
					statusBox.setSelectedItem("InActive");
				citizenshipField.setText(reservation.getCitizenship());
				nameField.setText(reservation.getName());
				costField.setText(String.valueOf(reservation.getCost()));
				airlineField.setText(reservation.getAirline());
			}
		}
	}


}
