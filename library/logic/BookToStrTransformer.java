package by.epam.tc.oop.task_6.library.logic;

import by.epam.tc.oop.task_6.library.entity.Book;

public class BookToStrTransformer {

	private static BookToStrTransformer instance = null;

	private BookToStrTransformer() {

	}

	public static BookToStrTransformer getInstance() {
		if (instance == null) {
			instance = new BookToStrTransformer();
		}
		return instance;
	}

	public String getInfo(Book b) {
		StringBuilder info = new StringBuilder();

		info.append("name : " + b.getName());
		info.append("; pages count : " + b.getNumbOfPages());
		info.append("; electronic : " + b.isElectronic());
		info.append("; description : " + b.getAdditionalInfo());
		info.append("; authors : " + getAuthorsList(b) + "\n");

		return info.toString();
	}

	public String getAuthorsList(Book b) {
		StringBuilder authorsList = new StringBuilder();
		for (int i = 0; i < b.getAuthors().size(); i++) {
			authorsList.append(b.getAuthors().get(i));
			if (i != b.getAuthors().size() - 1) {
				authorsList.append(", ");
			}
		}
		return authorsList.toString();
	}
}
