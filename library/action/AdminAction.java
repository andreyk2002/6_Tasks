package by.epam.tc.oop.task_6.library.action;

import java.util.*;

import by.epam.tc.oop.task_6.library.entity.*;
import by.epam.tc.oop.task_6.library.logic.BookCatalogLogic;

public class AdminAction extends UserAction {

	private static AdminAction instance = null;

	private AdminAction() {
		
	}

	public static AdminAction getInstance() {
		if (instance == null) {
			instance = new AdminAction();
		}
		return instance;
	}

	public boolean changeBookDescription(Library lib, String newDescription, String bookName, BookCatalog catalog) {
		Book book = BookCatalogLogic.searchBookByName(catalog.getBooks(), bookName);
		if (book != null) {
			book.setAdditionalInfo(newDescription);
			sentToAllYousers(lib, "The discription of the book " + bookName + " was changed");
			return true;
		}
		System.out.println("Can't find book with name " + bookName);
		return false;
	}

	public boolean addBook(Library lib,Book b, BookCatalog catalog) {
		sentToAllYousers(lib, "The book " + b.getName() + " was added to the library");
		return catalog.getBooks().add(b);

	}

	public boolean removeBook(Library lib,Book b, BookCatalog catalog) {
		sentToAllYousers(lib, "The book " + b.getName() + " was removed from the library");
		return catalog.getBooks().remove(b);
	}

	public boolean removeBook(Library lib,String bookName, BookCatalog catalog) {
		Book book = BookCatalogLogic.searchBookByName(catalog.getBooks(), bookName);
		if (book != null) {
			sentToAllYousers(lib, "The book " + bookName + " was removed from the library");
			return removeBook(lib, book, catalog);
		}
		System.out.println("Can't find book with name " + bookName);
		return false;
	}

	public void removeAuthor(Library lib,String authorName, BookCatalog catalog) {
		List<Book> authorBook = BookCatalogLogic.searchBookByAuthor(catalog, authorName);
		for (Book book : authorBook) {
			catalog.getBooks().remove(book);
		}
		sentToAllYousers(lib, "The author " + authorName + " was removed from the library");
	}

	public void sentToAllYousers(Library lib,String message) {
		for (User user : lib.getUsers()) {
			if (!(user instanceof Admin)) {
				user.getEmail().getMessages().add(message);
			}
		}
	}
}
