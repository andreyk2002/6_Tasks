package by.epam.tc.oop.task_6.library.entity;

import java.io.IOException;
import java.io.Serializable;

public class User implements Serializable{


	private static final long serialVersionUID = -3433121612926346389L;
	private final static short encoder = 20208;
	private String password;
	private String login;
	private Email email;
	private static Library userLibrary;

	public User() {
		this.login = "default login";
		this.password = "111111";
		this.email = new Email();
	}
	
	public User(String login, String password, Email email) throws IOException, IllegalArgumentException {
		setLogin(login);
		setPassword(password);
		setEmail(email);
	}
	protected static void setLibrary(Library lib) {
		userLibrary = lib;
	}

	public static Library getLibrary() {
		return userLibrary;
	}

	

	protected void setPassword(String password) {
		if (login == " " || login == null) {
			throw new IllegalArgumentException("Password can't be empty");
		}
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}

	protected void setLogin(String login) {
		if (login == " " || login == null) {
			throw new IllegalArgumentException("Login can't be empty");
		}
		this.login = login;
	}

	public Email getEmail() {
		return email;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	protected String getEncodedPassword() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < password.length(); i++) {
			char c = (char) (password.charAt(i) ^ encoder);
			b.append(c);
		}
		return b.toString();
	}

	public String getInfo() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getLogin());
		builder.append(", " + this.getEncodedPassword());
		builder.append(", " + this.getEmail().getEmailAddress() + ", ");
		if (this instanceof Admin) {
			builder.append("admin\n");
		} else {
			builder.append("user\n");
		}
		return builder.toString();
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + password.hashCode();
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [password=" + getEncodedPassword() + ", login=" + login + ", email=" + email + "]";
	}

}
