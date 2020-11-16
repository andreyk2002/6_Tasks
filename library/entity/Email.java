package by.epam.tc.oop.task_6.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements Serializable{

	private static final long serialVersionUID = 200602971167688738L;
	private String emailAddress;
	private List<String> messages;

	public Email() {
		this("0@gmail.com");
	}

	public Email(String emailAddress) throws IllegalArgumentException {
		setEmailAddress(emailAddress);
		this.messages = new ArrayList<String>();
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) throws IllegalArgumentException {
		Pattern emailPattern = Pattern.compile("^\\w+@[a-zA-Z]{2,5}\\.(ru|com|by|ua)$");
		Matcher m = emailPattern.matcher(emailAddress);

		if (m.matches()) {
			this.emailAddress = emailAddress;
			return;
		}
		throw new IllegalArgumentException("Email address " + emailAddress + " not allowed here");
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
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
		Email other = (Email) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Email [emailAddress=" + emailAddress + ", messages=" + messages + "]";
	}

}
