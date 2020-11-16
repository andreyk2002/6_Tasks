package by.epam.tc.oop.task_6.library.entity;

import java.io.Serializable;
import java.util.*;

public class BookCatalog implements Serializable{
	
	private static final long serialVersionUID = 8635619835734622348L;
	private List<Book> books;

	public BookCatalog() {
		this(new ArrayList<Book>());
	}

	public BookCatalog(List<Book> books) {
		super();
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
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
		BookCatalog other = (BookCatalog) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookCatalog [books=" + books + "]";
	}

}
