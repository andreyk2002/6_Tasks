package by.epam.tc.oop.task_6.library.action;

import by.epam.tc.oop.task_6.library.entity.BookCatalog;
import by.epam.tc.oop.task_6.library.entity.Library;
import by.epam.tc.oop.task_6.library.entity.User;

public interface WorkWithLibrary {
	public boolean browseCatalog(Library lib, BookCatalog b, int page);

	public void searchBookByAuthor(Library lib, BookCatalog b, String authorName);

	public void searchBookByName(Library lib, BookCatalog b, String name);

	public void sentEmail(Library lib, User sender, String message);
}
