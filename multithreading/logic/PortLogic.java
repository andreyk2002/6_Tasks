package by.epam_tc.multithreading.logic;

import by.epam_tc.multithreading.entity.*;

public class PortLogic {

	private Port port;
	private BerthThread[] threads;
	private final int DISCARGING_SPEED_COEFFICIENT = 4;

	public PortLogic(Port p) {
		this.port = p;
		threads = new BerthThread[p.BERTH_COUNT];
		for (int i = 0; i < p.BERTH_COUNT; i++) {
			threads[i] = new BerthThread(this);
			threads[i].start();
		}
	}

	public PortLogic() {
		this(new Port());
	}

	// спецификатор пропущен не просто так
	void setProcessed(boolean processed) {
	}

	public synchronized void handleShip(Ship sh) {
		port.getWatingShips().add(sh);
		notify();
	}

	public void addShip(Ship s) {
		port.getWatingShips().add(s);
	}

	public Port getPort() {
		return port;
	}

	public synchronized void stopPort() {

		for (var th : threads) {
			th.end();
		}

	}

	public boolean isEmptyQueue() {
		return port.getWatingShips().isEmpty();
	}

	public void unloadShip(Ship sh) throws Exception {

		while (sh.getCurrentWeight() + port.getCurrentWeight() > port.CAPACITY) {
			int deltaWeight;
			deltaWeight = sh.getCurrentWeight() - (port.CAPACITY - port.getCurrentWeight());
			Thread.sleep(deltaWeight);// имитируем разгрузку корабля
			sh.setCurrentWeight(sh.getCurrentWeight() - deltaWeight);
			port.setCurrentWeight(port.getCurrentWeight() + deltaWeight);
			if (deltaWeight != 0) {
				System.out.println("port load changed : " + port.getCurrentWeight() + "/" + port.CAPACITY);
				notifyAll();
			}
			wait();
		}

		int deltaWeight = sh.getCurrentWeight();
		Thread.sleep(deltaWeight);
		sh.setCurrentWeight(0);
		port.setCurrentWeight(port.getCurrentWeight() + deltaWeight);
		notifyAll();
	}

	public void loadShip(Ship sh) throws Exception {

		while (port.getCurrentWeight() + sh.getCurrentWeight() < sh.MAX_WEIGHT) {
			int deltaWeight = port.getCurrentWeight();
			Thread.sleep(deltaWeight / DISCARGING_SPEED_COEFFICIENT);// имитируем загрузку корабля
			port.setCurrentWeight(0);
			sh.setCurrentWeight(sh.getCurrentWeight() + deltaWeight);
			if (deltaWeight != 0) {
				System.out.println("port load changed : " + port.getCurrentWeight() + "/" + port.CAPACITY);
			}
			notifyAll();
			wait();

		}

		int deltaWeight = sh.MAX_WEIGHT - sh.getCurrentWeight();
		Thread.sleep(deltaWeight / DISCARGING_SPEED_COEFFICIENT);// имитируем загрузку корабля
		sh.setCurrentWeight(sh.MAX_WEIGHT);
		port.setCurrentWeight(port.getCurrentWeight() - deltaWeight);
		notifyAll();

	}

}
