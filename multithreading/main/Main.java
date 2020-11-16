package by.epam_tc.multithreading.main;

import by.epam_tc.multithreading.entity.*;
import by.epam_tc.multithreading.logic.PortLogic;

public class Main {

	private static Thread mainThread;

	public static Thread getMainThread() {
		return mainThread;
	}

	public static void main(String[] argc) {
		mainThread = Thread.currentThread();
		Port port = new Port();
		try {
			port = new Port(5, 30_000, 15_000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Ship[] sh = new Ship[10];
		try {
			sh[0] = new Ship(100, 50, "fishing boat", true);
			sh[1] = new Ship(250, 100, "big boat", false);
			sh[2] = new Ship(1000, 0, "fishing ship", false);
			sh[3] = new Ship(500, 450, "yacht", true);
			sh[4] = new Ship(7500, 6000, "war ship", true);
			sh[5] = new Ship(35_000, 0, "cruise liner", false);
			sh[6] = new Ship(1200, 240, "scientific ship", true);
			sh[7] = new Ship(40_000, 38_000, "Abramovich's yacht", true);
			sh[8] = new Ship(5000, 0, "Big ship", false);
			sh[9] = new Ship(40, 20, "small boat", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PortLogic handler = new PortLogic(port);

		for (var ship : sh) {
			try {
				handler.handleShip(ship);

			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}

		handler.stopPort();

	}
}
