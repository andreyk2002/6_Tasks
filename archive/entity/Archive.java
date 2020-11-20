package by.epam_tc.sockets.archive.entity;

import java.io.Serializable;
import java.util.*;

public class Archive implements Serializable{
	
	private static final long serialVersionUID = 7358269455492225970L;
	private Set<Case>cases;
	
	public Archive(Set<Case>cases) {
		this.cases = cases;
	}
	
	public Archive() {
		this.cases = new HashSet<Case>();
	}

	public Set<Case> getCases() {
		return cases;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cases == null) ? 0 : cases.hashCode());
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
		Archive other = (Archive) obj;
		if (cases == null) {
			if (other.cases != null)
				return false;
		} else if (!cases.equals(other.cases))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Archieve [cases=" + cases + "]";
	}
	
	
}
