package by.epam_tc.multithreading.entity;

import java.io.Serializable;
import java.util.*;

import by.epam_tc.multithreading.exceptions.PortException;

public class Port implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Queue<Ship> watingShips = new LinkedList<Ship>();
	public final int BERTH_COUNT;
	public final int CAPACITY;
	private int currentWeight;

	public Port(int berthCount, int capacity, int currentWeight) throws Exception {
		if (capacity < 0) {
			throw new PortException("Capacity of the port can't be negative");
		}
		this.CAPACITY = capacity;
		setCurrentWeight(currentWeight);

		this.BERTH_COUNT = berthCount;

	}

	public Port(int berthCount, int capacity) throws Exception {
		this(berthCount, capacity, 0);
	}

	public Port() {
		this.BERTH_COUNT = 0;
		this.CAPACITY = 0;
		this.currentWeight = 0;
	}

	public int getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(int currentWeight) throws Exception {
		if (currentWeight < 0) {
			throw new PortException("Weight can' t be negative !");
		}
		if (this.currentWeight > CAPACITY) {
			throw new PortException("Port is overrloaded");
		}
		this.currentWeight = currentWeight;
	}

	public Queue<Ship> getWatingShips() {
		return watingShips;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + BERTH_COUNT;
		result = prime * result + CAPACITY;
		result = prime * result + currentWeight;
		result = prime * result + ((watingShips == null) ? 0 : watingShips.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Port other = (Port) obj;
		if (BERTH_COUNT != other.BERTH_COUNT)
			return false;
		if (CAPACITY != other.CAPACITY)
			return false;
		if (currentWeight != other.currentWeight)
			return false;
		if (watingShips == null) {
			if (other.watingShips != null)
				return false;
		} else if (!watingShips.equals(other.watingShips))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Port [watingShips=" + watingShips + ", BERTH_COUNT=" + BERTH_COUNT + ", CAPACITY=" + CAPACITY
				+ ", currentWeight=" + currentWeight + "]";
	}

}
