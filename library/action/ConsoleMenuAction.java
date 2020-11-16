package by.epam.tc.oop.task_6.library.action;

import java.util.*;


import by.epam.tc.oop.task_6.library.dao.BookCatalogIO;
import by.epam.tc.oop.task_6.library.entity.Book;
import by.epam.tc.oop.task_6.library.entity.User;
import by.epam.tc.oop.task_6.library.logic.BookCatalogLogic;

public class ConsoleMenuAction {
	
	private static ConsoleMenuAction instance = null;
	private UserAction uAction = UserAction.getInstance();
	private AdminAction aAction = AdminAction.getInstance();

	private ConsoleMenuAction() {

	}

	public static ConsoleMenuAction getInstance() {
		if (instance == null) {
			instance = new ConsoleMenuAction();
		}
		return instance;
	}

	private Scanner sc = new Scanner(System.in);

	public void choseAction(User user, int max) {
		int command;
		boolean exit = false;
		while (!exit) {
			Collections.sort(User.getLibrary().getCatalog().getBooks());
			command = readInt("Chose your command : ", max);

			switch (command) {
			case 0:
				BookCatalogIO.writeBooksInfoToFile(User.getLibrary().getCatalog());
				exit = true;
				break;
			case 1:
				browse();
				break;
			case 2:
				searchBook();
				break;
			case 3:
				searchBookByAuthor();
				break;
			case 4:
				printEmails(user);
				break;
			case 5:
				sentEmail(user);
				break;
			case 6:
				deleteBook(user);
				break;
			case 7:
				deleteBookByAuthor(user);
				break;
			case 8:
				addBook(user);
				break;
			case 9:
				modifyDescription(user);
				break;
			}
		}
	}

	public void browse() {
		int page = 0;
		int viewNext = 1;
		while (viewNext == 1) {
			if (!(uAction.browseCatalog(User.getLibrary(), User.getLibrary().getCatalog(), page))) {
				page = -1;
			} else {
				viewNext = readInt("1 - show next page; 0 - stop :", 1);
			}
			page++;
		}
	}

	public String readString(String message) {
		System.out.print(message);
		String result;
		if (sc.hasNextLine()) {
			result = sc.nextLine();
		} else {
			result = sc.next();
		}

		while (result == "") {
			System.out.println(message);
			result = sc.next();
		}
		return result;
	}

	public void searchBook() {
		String bookName = readString("Enter the name of the book : ");
		uAction.searchBookByName(User.getLibrary(), User.getLibrary().getCatalog(), bookName);
	}

	public void searchBookByAuthor() {
		String authorName = readString("Enter author name : ");
		uAction.searchBookByAuthor(User.getLibrary(), User.getLibrary().getCatalog(), authorName);
	}

	public void printEmails(User user) {
		for (String str : user.getEmail().getMessages()) {
			System.out.println(str);
			System.out.println("--------------------------\n\n");
		}
	}

	public void sentEmail(User u) {
		String msg = readString("Enter the text of the message : ");
		uAction.sentEmail(User.getLibrary(),u, msg );
	}

	public void deleteBook(User u) {
		String bookName = readString("Enter name of the book to be deleted : ");
		aAction.removeBook(User.getLibrary(), bookName, User.getLibrary().getCatalog());

	}

	public void deleteBookByAuthor(User u) {
		String authorName = readString("Enter the name of the author of deleted books : ");
		aAction.removeAuthor(User.getLibrary(), authorName, User.getLibrary().getCatalog());
	}

	public void addBook(User u) {
		int numbOfPages;
		String description;
		String name;
		List<String> authors = new ArrayList<String>();

		name = readString("Enter the name of the book : ");
		String command;
		do {
			authors.add(readString("Enter the name of the author of the book."));
			command = readString(" Enter \"+\" to add more authors");
		} while (command == "+");

		numbOfPages = readInt("Enter number of pages in the book : ", 2000);
		description = readString("Enter some additional info about book : ");
		Book newBook;
		boolean isElectronic;
		isElectronic = readInt("0 - paper book; 1 - electronic", 1) == 0 ? false : true;
		newBook = new Book(name, numbOfPages, isElectronic, description, authors);
		aAction.addBook(User.getLibrary(), newBook, User.getLibrary().getCatalog());
	}

	public void modifyDescription(User user) {
		String name = readString("Enter book name : ");
		Book book = BookCatalogLogic.searchBookByName(User.getLibrary().getCatalog().getBooks(), name);
		String newDescription = readString("Enter the new description of the book : ");

		book.setAdditionalInfo(newDescription);
	}

	public int readInt(String message, int max) {
		int result = -1;

		while (result < 0 || result > max) {
			System.out.println(message);
			if (sc.hasNextInt()) {
				result = sc.nextInt();
			} else {
				sc.next();
			}
		}
		sc.nextLine();
		return result;
	}
}
