package by.epam.tc.oop.task_6.library.action;

import java.util.*;

import by.epam.tc.oop.task_6.library.entity.*;
import by.epam.tc.oop.task_6.library.logic.BookCatalogLogic;

public class UserAction implements WorkWithLibrary{

	private static UserAction instance = null;
	
	protected UserAction(){
		
	}
	
	public static UserAction getInstance() {
		if(instance == null) {
			instance = new UserAction();
		}
		return instance;
	}
	

	public boolean browseCatalog(Library lib, BookCatalog b, int page) {
		return BookCatalogLogic.browsePage(b, page);
	}

	public void searchBookByAuthor(Library lib, BookCatalog b, String authorName) {
		List<Book> books = BookCatalogLogic.searchBookByAuthor(b, authorName);
		if (books.isEmpty()) {
			System.out.println("No avaible books of author " + authorName);
			return;
		}
		for (Book book : books) {
			System.out.println(book.toString());
		}
	}

	public void searchBookByName(Library lib, BookCatalog b, String name) {
		Book book = BookCatalogLogic.searchBookByName(b.getBooks(), name);
		if (book != null) {
			System.out.println(book.toString());
			return;
		}
		System.out.println("Can't find book with name " + name);
	}

	public void sentEmail(Library lib, User sender, String message) {
		String from = "from : " + sender.getEmail().getEmailAddress() + "(" + sender.getLogin() + ")";
		for (var user : lib.getUsers()) {
			if (user instanceof Admin) {
				user.getEmail().getMessages().add(message + "\n" + from);
			}
		}
	}

}
