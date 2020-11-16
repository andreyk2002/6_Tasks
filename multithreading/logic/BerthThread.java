package by.epam_tc.multithreading.logic;

import by.epam_tc.multithreading.entity.*;

public class BerthThread extends Thread {

	private boolean stop = false;
	private PortLogic handler;

	public BerthThread(PortLogic pl) {
		this.handler = pl;
	}

	public BerthThread() {
		this.handler = new PortLogic();
	}

	@Override
	public void run() {
		while (!stop || !handler.getPort().getWatingShips().isEmpty()) {
			try {
				processShip();
			} catch (InterruptedException e) {
				System.out.println("Interrupted exception was called");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void processShip() throws Exception {
		synchronized (handler) {

			while (handler.isEmptyQueue()) {
				if (stop) {
					return;
				}
				handler.wait();
			}

			Ship ship = handler.getPort().getWatingShips().poll();
			if (ship.isUnload()) {

				handler.unloadShip(ship);
				System.out.println("Ship " + ship.getName() + " was unloaded in the port");
				System.out.println("port load changed : " + handler.getPort().getCurrentWeight() + "/"
						+ handler.getPort().CAPACITY);

			} else {

				handler.loadShip(ship);
				System.out.println("Ship " + ship.getName() + " was loaded in the port");
				System.out.println("port load changed : " + handler.getPort().getCurrentWeight() + "/"
						+ handler.getPort().CAPACITY);

			}
		}
	}

	public boolean isEnded() {
		return stop;
	}

	public void end() {
		stop = true;
	}
}
