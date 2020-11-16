package by.epam.tc.oop.task_6.library.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.tc.oop.task_6.library.entity.Book;
import by.epam.tc.oop.task_6.library.entity.BookCatalog;

public class BookCatalogIO {
	private final String CATALOG_PATH = "resources\\library\\catalog.txt";
	
	private static BookCatalogIO instance = null;
	
	private BookCatalogIO(){
		
	}
	
	public static BookCatalogIO getInstance() {
		if(instance == null) {
			instance = new BookCatalogIO();
		}
		return instance;
	}
	
	
	public BookCatalog getCatalog() throws IOException {
		BookCatalog catalog = new BookCatalog();
		BufferedReader reader = new BufferedReader(new FileReader(CATALOG_PATH));

		String line;
		while ((line = reader.readLine()) != null) {
			Book book = parseFromString(line);
			catalog.getBooks().add(book);
		}
		reader.close();
		return catalog;
	}
	
	private Book parseFromString(String line) {
		String name = "";
		int numbOfPages = -1;
		boolean isElectronic = false;
		String additionalInfo = "";
		String[] authorsList = new String[0];

		String[] words = line.split(";");
		int i = 0;

		String pattern = "(.*)(:\\s)(.*)";
		Pattern r = Pattern.compile(pattern);

		Matcher nameMatcher = r.matcher(words[i++]);
		if (nameMatcher.find()) {
			name = nameMatcher.group(3);
		}

		Matcher pagesMatcher = r.matcher(words[i++]);
		if (pagesMatcher.find()) {
			numbOfPages = Integer.parseInt(pagesMatcher.group(3));
		}

		Matcher electronicMatcher = r.matcher(words[i++]);
		if (electronicMatcher.find()) {
			isElectronic = Boolean.parseBoolean(electronicMatcher.group(3));
		}

		Matcher infoMatcher = r.matcher(words[i++]);
		if (infoMatcher.find()) {
			additionalInfo = infoMatcher.group(3);
		}

		Matcher authorMatcher = r.matcher(words[i]);

		if (authorMatcher.find()) {
			authorsList = authorMatcher.group(3).split(",");
		}
		return new Book(name, numbOfPages, isElectronic, additionalInfo, authorsList);
	}

	public static void writeBooksInfoToFile(BookCatalog catalog) {
		boolean shouldAdd = false;
		for (Book currentBook : catalog.getBooks()) {
			try {
				currentBook.writeInfo(currentBook, shouldAdd);
				shouldAdd = true;

			} catch (IOException e) {
				System.out.println("Error during writing books info : " + e.getMessage());
			}
		}
	}
}
