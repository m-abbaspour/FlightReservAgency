package sait.frms.application;

import sait.frms.gui.MainWindow;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("Main program started");
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
