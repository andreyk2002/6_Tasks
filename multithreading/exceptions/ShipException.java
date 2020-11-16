package by.epam_tc.multithreading.exceptions;

import by.epam_tc.multithreading.entity.Ship;

public class ShipException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShipException(Ship ship, String message) {
		super(ship.getName() + " : " + message);
	}

	public ShipException() {
		super();
	}

}
