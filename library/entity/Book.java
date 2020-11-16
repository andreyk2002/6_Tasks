package by.epam.tc.oop.task_6.library.entity;

import java.io.*;
import java.util.*;

import by.epam.tc.oop.task_6.library.logic.BookToStrTransformer;

public class Book implements Comparable<Book>, Serializable {

	private static final long serialVersionUID = 1114486456226688349L;
	private String name;
	private int numbOfPages;
	private boolean isElectronic;
	private String additionalInfo;
	private List<String> authors = new ArrayList<String>();
	private final String CATALOG_PATH = "resources\\library\\catalog.txt";

	public Book() {

	}

	public Book(String name, int numbOfPages, boolean isElectronic, String additionalInfo, List<String> authors) {
		super();
		setName(name);
		this.authors = authors;
		this.numbOfPages = numbOfPages;
		this.isElectronic = isElectronic;
		setAdditionalInfo(additionalInfo);
	}

	public Book(String name, int numbOfPages, boolean isElectronic, List<String> authors) {
		this(name, numbOfPages, isElectronic, "", authors);
	}

	public Book(String name, int numbOfPages, boolean isElectronic, String additionalInfo, String... authors) {
		this(name, numbOfPages, isElectronic, additionalInfo, new ArrayList<String>());
		for (String s : authors) {
			this.authors.add(s);
		}

	}

	public Book(String name, int publishYear, int numbOfPages, boolean isElectronic, String... authors) {
		this(name, numbOfPages, isElectronic, "", authors);
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		additionalInfo.replaceAll(";", ",");
		/*
		 * При записи и чтении книжного католога, поля Book разделяются точкой с
		 * запятой. Чтобы избежать ошибок при чтении заменяем все "пользовательские"
		 * точки с запятой
		 */
		this.additionalInfo = additionalInfo;
	}

	protected void setName(String name) {
		name.replaceAll(";", ",");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getNumbOfPages() {
		return numbOfPages;
	}

	public boolean isElectronic() {
		return isElectronic;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void writeInfo(Book b, boolean shouldAdd) throws IOException {// remove book from arguments
		File catologFile = new File(CATALOG_PATH);
		FileWriter writer = new FileWriter(catologFile, shouldAdd);
		writer.append(BookToStrTransformer.getInstance().getInfo(b));
		writer.close();
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", numbOfPages=" + numbOfPages + ", isElectronic=" + isElectronic
				+ ", additionalInfo=" + additionalInfo + ", authors=" + authors + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalInfo == null) ? 0 : additionalInfo.hashCode());
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + (isElectronic ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numbOfPages;
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
		Book other = (Book) obj;
		if (additionalInfo == null) {
			if (other.additionalInfo != null)
				return false;
		} else if (!additionalInfo.equals(other.additionalInfo))
			return false;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (isElectronic != other.isElectronic)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numbOfPages != other.numbOfPages)
			return false;
		return true;
	}

	@Override
	public int compareTo(Book b) {
		return this.name.compareTo(b.getName());
	}

}
