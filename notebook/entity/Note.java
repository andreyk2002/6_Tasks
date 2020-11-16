package by.epam_tc.task_6.notebook.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Note implements Serializable{
	
	private static final long serialVersionUID = -6638056923064611130L;
	private String theme;
	private LocalDate createDate;
	private String email;
	private String message;

	public Note() {
		this("", null, "", "");
	}

	public Note(String theme, LocalDate createDate, String email, String message) {
		super();
		this.theme = theme;
		this.createDate = createDate;
		this.email = email;
		this.message = message;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
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
		Note other = (Note) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Note [theme=" + theme + ", createDate=" + createDate + ", email=" + email + ", message=" + message
				+ "]";
	}

}
