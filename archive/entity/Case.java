package by.epam_tc.sockets.archive.entity;

import java.io.Serializable;

public class Case implements Serializable {

	private static final long serialVersionUID = 216037178329450051L;
	private String person;
	private String content;
	private final long id;
	private static long currentId = 0;

	public Case() {
		id = currentId++;
	}

	public Case(String person, String content) {
		this();
		this.person = person;
		this.content = content;
	}

	public Case(String person, String content, long id) throws Exception {
		setId(id);
		this.person = person;
		this.content = content;
		this.id = currentId++;

	}

	public void setId(long id) throws Exception {
		if (id < currentId) {
			throw new Exception("ID " + id + " currently in use");
		}
		currentId = id;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPerson() {
		return person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		Case other = (Case) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Case [person=" + person + ", content=" + content + ", id=" + id + "]";
	}

}
