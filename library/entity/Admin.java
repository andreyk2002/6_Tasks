package by.epam.tc.oop.task_6.library.entity;

import java.io.IOException;

public class Admin extends User{

	private static final long serialVersionUID = 8877825042423661462L;

	public Admin(String password, String login,Email email) throws IOException {
		super(password, login,email);
	}
	
	public Admin() {
		super();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin : " + super.toString();
	}
	
	
}
