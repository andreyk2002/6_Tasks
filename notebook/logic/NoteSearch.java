package by.epam_tc.task_6.notebook.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import by.epam_tc.task_6.notebook.entity.Note;
import by.epam_tc.task_6.notebook.entity.Notebook;

public class NoteSearch {

	private NotebookLogic logic;
	private Notebook notebook;
	private Scanner sc = new Scanner(System.in);

	public NoteSearch(Notebook n) {
		this.notebook = n;
		logic = new NotebookLogic(n);
	}

	public NoteSearch() {
		this(new Notebook());
	}

	private Notebook findByEmail(String emailAddress, Notebook notebook) throws Exception {
		logic.sortByEmail();
		List<Note> newNotes = new ArrayList<Note>();

		if (!logic.isEmail(emailAddress)) {
			throw new Exception("Wrong format for email address : " + emailAddress + "\n");
		}

		for (Note note : notebook.getNotes()) {
			if (note.getEmail().equals(emailAddress)) {
				newNotes.add(note);
			}
		}
		checkEmpty(newNotes, "Can't find notes with email \"" + emailAddress + "\"");
		return new Notebook(newNotes);

	}

	public Notebook findByEmail(String emailAddress) throws Exception {
		System.out.println("Search notes by email " + emailAddress + " : ");
		return findByEmail(emailAddress, this.notebook);
	}

	private Notebook findByTheme(String theme, Notebook notebook) {
		logic.sortByTheme();
		List<Note> newNotes = new ArrayList<Note>();
		for (Note note : notebook.getNotes()) {
			if (note.getTheme().equals(theme)) {
				newNotes.add(note);
			}
		}
		checkEmpty(newNotes, "Can't find notes with theme \"" + theme + "\"");
		return new Notebook(newNotes);
	}

	public Notebook findByTheme(String theme) {
		System.out.println("Search notes by theme " + theme + " : ");
		return findByTheme(theme, this.notebook);
	}

	private Notebook findByDate(LocalDate date, Notebook notebook) {
		logic.sortByDate();
		List<Note> newNotes = new ArrayList<Note>();
		for (Note note : notebook.getNotes()) {
			if (note.getCreateDate().equals(date)) {
				newNotes.add(note);
			}
		}
		checkEmpty(newNotes, "Can't find notes with date \"" + date.toString() + "\"");
		return new Notebook(newNotes);
	}

	public Notebook findByDate(LocalDate date) {
		System.out.println("Search notes by date " + date.toString() + " : ");
		return findByDate(date, this.notebook);
	}

	private Notebook findByWord(String word, Notebook notebook) {
		List<Note> newNotes = new ArrayList<Note>();
		Pattern p = Pattern.compile(word);

		for (Note note : notebook.getNotes()) {
			Matcher m = p.matcher(note.getMessage());
			if (m.find()) {
				newNotes.add(note);
			}
		}
		checkEmpty(newNotes, "Can't find notes with word \"" + word + "\"");
		return new Notebook(newNotes);
	}

	public Notebook findByWord(String word) {
		System.out.println("Search notes by word  \"" + word + "\" in message : ");
		return findByWord(word, this.notebook);
	}

	public Notebook findByUserParams(String... paramsNames) throws Exception {

		Notebook notebook = this.notebook;
		
		for (String param : paramsNames) {
			if (notebook.getNotes().isEmpty()) {
				return notebook;
			}
			
			if (param.equalsIgnoreCase("date")) {
				String date = readString("Enter the notes date in format yyyy-MM-dd: ", NotebookLogic.DATE_PATTERN);
				char divider = NotebookLogic.findDivider(date);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy" + divider + "MM" + divider + "dd");
				LocalDate createDate = LocalDate.parse(date, formatter);
				notebook = findByDate(createDate, notebook);
			} else if (param.equalsIgnoreCase("word")) {
				String word = readString("Enter the word in the notes : ", "[^\\s]+");
				notebook = findByWord(word, notebook);
			} else if (param.equalsIgnoreCase("theme")) {
				String theme = readString("Enter the theme of the message : ", ".+");
				notebook = findByTheme(theme, notebook);
			} else if (param.equalsIgnoreCase("email")) {
				String email = readString("Enter the email of the message : ", NotebookLogic.EMAIL_PATTERN);
				notebook = findByEmail(email, notebook);
			} else {
				throw new InputMismatchException("Unknown parametr " + param + " was find");
			}
		
		}
		return notebook;
	}

	private void checkEmpty(List<Note> newNotes, String msg) {
		if (newNotes.isEmpty()) {
			System.out.println(msg);
		}
	}

	public String readString(String msg, String neededPattern) {

		String res;
		// sc.skip("^\n$");
		do {
			System.out.println(msg);
			res = sc.nextLine();
		} while (!res.matches(neededPattern));

		// sc.close();
		return res;
	}

}
