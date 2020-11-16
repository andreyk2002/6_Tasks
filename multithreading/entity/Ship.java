package by.epam_tc.multithreading.entity;

import java.io.Serializable;

import by.epam_tc.multithreading.exceptions.ShipException;

public class Ship implements Serializable {

	private static final long serialVersionUID = 1L;
	public final int MAX_WEIGHT;
	private int currentWeight;
	private String name;
	private boolean isUnload;

	public Ship(int max, int currentWeight, String name, boolean isUnload) throws Exception {
		this.name = name;
		if (max < 0 || currentWeight < 0) {
			throw new ShipException(this, "Ship weight can't be less than 0");
		}
		MAX_WEIGHT = max;
		if (currentWeight > max) {
			throw new ShipException(this, "Ship is overloaded!");
		}
		this.currentWeight = currentWeight;

		this.isUnload = isUnload;
	}

	public Ship() {
		this.currentWeight = 0;
		this.MAX_WEIGHT = 0;
		this.name = "default name";
		isUnload = false;
	}

	public boolean isUnload() {
		return isUnload;
	}

	public void setUnload(boolean isUnload) {
		this.isUnload = isUnload;
	}

	public int getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_WEIGHT;
		result = prime * result + currentWeight;
		result = prime * result + (isUnload ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Ship other = (Ship) obj;
		if (MAX_WEIGHT != other.MAX_WEIGHT)
			return false;
		if (currentWeight != other.currentWeight)
			return false;
		if (isUnload != other.isUnload)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ship [MAX_WEIGHT=" + MAX_WEIGHT + ", currentWeight=" + currentWeight + ", name=" + name + ", isUnload="
				+ isUnload + "]";
	}

}
