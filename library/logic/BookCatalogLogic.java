package by.epam.tc.oop.task_6.library.logic;


import java.util.*;

import by.epam.tc.oop.task_6.library.entity.*;

public class BookCatalogLogic {

	private static final int LINES_IN_PAGE = 20;// сколько книг выводим на страницу
	private static BookCatalogLogic instance = null;

	private BookCatalogLogic() {

	}

	public BookCatalogLogic getInstance() {
		if (instance == null) {
			instance = new BookCatalogLogic();
		}
		return instance;
	}

	public static boolean browsePage(BookCatalog b, int page) {
		int begin = page * LINES_IN_PAGE;
		if (begin >= b.getBooks().size()) {
			return false;
		}

		for (int i = 0; i + begin < b.getBooks().size() && i < LINES_IN_PAGE; i++) {
			System.out.print(BookToStrTransformer.getInstance().getInfo(b.getBooks().get(i + begin)));
		}
		return true;
	}

	public static Book searchBookByName(List<Book> list, String bookName) {
		int mid = list.size() / 2;
		if (list.isEmpty()) {
			return null;
		}
		Book book = list.get(mid);

		if (book.getName().toLowerCase().compareTo(bookName.toLowerCase()) == 0) {
			return book;
		}

		else if (book.getName().toLowerCase().compareTo(bookName.toLowerCase()) > 0) {
			return searchBookByName(list.subList(0, mid), bookName);
		}

		else {
			return searchBookByName(list.subList(mid + 1, list.size()), bookName);
		}

	}

	public static List<Book> searchBookByAuthor(BookCatalog b, String authorName) {
		List<Book> booksByAuthor = new ArrayList<Book>();
		for (Book book : b.getBooks()) {
			if (findAuthor(book.getAuthors(), authorName)) {
				booksByAuthor.add(book);
			}
		}
		return booksByAuthor;
	}

	private static boolean findAuthor(List<String> authors, String authorName) {
		for (String author : authors) {
			if (author.equalsIgnoreCase(authorName)) {
				return true;
			}
		}
		return false;
	}
}
