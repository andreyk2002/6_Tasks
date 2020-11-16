package by.epam.tc.oop.task_6.library.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import by.epam.tc.oop.task_6.library.dao.BookCatalogIO;
import by.epam.tc.oop.task_6.library.dao.UserIO;

public class Library implements Serializable{
	
	private static final long serialVersionUID = 8834342294365313510L;
	private UserIO fileUser = UserIO.getInstance();
	private List<User> users;
	private BookCatalog catalog = new BookCatalog();
	private BookCatalogIO fileBookCalalog = BookCatalogIO.getInstance();
	
	public Library() throws IOException{
		initUsers();
		getLibInfo();
	}

	private void initUsers() throws IOException{
		User.setLibrary(this);
		users = fileUser.getUsers();		
	}

	private void getLibInfo() throws IOException{
		BookCatalog catalogFromFile = fileBookCalalog.getCatalog();
		catalog = catalogFromFile;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public void setCatalog(BookCatalog catalog) {
		this.catalog = catalog;
	}

	public List<User> getUsers() {
		return users;
	}

	public BookCatalog getCatalog() {
		return catalog;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catalog == null) ? 0 : catalog.hashCode());
		result = prime * result + ((fileBookCalalog == null) ? 0 : fileBookCalalog.hashCode());
		result = prime * result + ((fileUser == null) ? 0 : fileUser.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Library other = (Library) obj;
		if (catalog == null) {
			if (other.catalog != null)
				return false;
		} else if (!catalog.equals(other.catalog))
			return false;
		if (fileBookCalalog == null) {
			if (other.fileBookCalalog != null)
				return false;
		} else if (!fileBookCalalog.equals(other.fileBookCalalog))
			return false;
		if (fileUser == null) {
			if (other.fileUser != null)
				return false;
		} else if (!fileUser.equals(other.fileUser))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Library [fileUser=" + fileUser + ", users=" + users + ", catalog=" + catalog + ", fileBookCalalog="
				+ fileBookCalalog + "]";
	}
	
	

}
