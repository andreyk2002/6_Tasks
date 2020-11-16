package by.epam_tc.task_6.notebook.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam_tc.task_6.notebook.entity.*;

public class NotebookLogic {
	
	private List<Note> notes;
	private static final String NOTE_PATTERN = "([Tt]heme\\s:\\s)(.*)(\n[Dd]ate\\s:\\s)(.*)(\n[Ee]mail\\s:\\s)(.*)(\n[Mm]essage\\s:\\s)(.*)";
	public static final String EMAIL_PATTERN = "^\\w+@[a-zA-Z]{2,5}\\.(ru|com|by|ua)$";
	public static final String DATE_PATTERN = "\\d{4}[-\\/\\|_\\s][0-1]\\d[-\\/\\|_\\s][0-3]\\d";
	public static Comparator<Note> NoteThemeComp;
	public static Comparator<Note> NoteDateComp;
	public static Comparator<Note> NoteEmailComp;

	static {
		NoteThemeComp = new Comparator<Note>() {

			@Override
			public int compare(Note n1, Note n2) {
				return (int) (n1.getTheme().compareTo(n2.getTheme()));
			}
		};
		NoteDateComp = new Comparator<Note>() {

			@Override
			public int compare(Note n1, Note n2) {
				return (int) (n1.getCreateDate().compareTo(n2.getCreateDate()));
			}
		};

		NoteEmailComp = new Comparator<Note>() {

			@Override
			public int compare(Note n1, Note n2) {
				return (int) (n1.getEmail().compareTo(n2.getEmail()));
			}
		};
	}
		
	public NotebookLogic(Notebook n) {
		this.notes = n.getNotes();
	}

	public NotebookLogic() {
		this(new Notebook());
	}

	public void sortByDate() {
		notes.sort(NoteDateComp);

	}

	public void sortByTheme() {
		notes.sort(NoteThemeComp);
	}

	public void sortByEmail() {
		notes.sort(NoteEmailComp);
	}

	public void addNote(Note note) {
		this.notes.add(note);
	}

	public void addNote(String noteText) throws Exception {
		String noteTheme;
		LocalDate createDate = null;
		String email;
		String message;
		Pattern p;
		p = Pattern.compile(NOTE_PATTERN, Pattern.DOTALL);
		Matcher m = p.matcher(noteText);
		if (m.find()) {
			noteTheme = m.group(2);
			String dateString = m.group(4);
			if (isDate(dateString)) {
				char divider = findDivider(dateString);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy" + divider + "MM" + divider + "dd");
				createDate = LocalDate.parse(m.group(4), formatter);
			} else {
				throw new Exception("Wrong date format : " + m.group(4) + " . Date format should be yyyy-MM-dd");// ?
			}
			if (isEmail(m.group(6))) {
				email = m.group(6);
			} else {
				throw new Exception("Wrong format of email adress : " + m.group(6));
			}
			message = m.group(8);

		} else {
			throw new Exception("Can' t read the note");
		}

		this.notes.add(new Note(noteTheme, createDate, email, message));
	}

	public boolean isEmail(String s) {
		Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
		Matcher m = emailPattern.matcher(s);

		return m.matches();
	}

	public boolean isDate(String s) {

		String dividers = "[-\\/\\|_\\s]";
		String[] date = s.split(dividers);
		int monthStatus = matchesMonth(date[1]);
		if (monthStatus > 0) {
			// 0 - не месяц, 1 - февраль(28 дней), 2 - 30 дней, 3 - 31 день
			if (!matchesYear(date[0])) {
				return false;
			}
			if (monthStatus == 1 && date[2].matches("^0[1-9]$|^1[0-9]$|^2[0-8]$")) {
				return true;
			}

			if (monthStatus == 2 && date[2].matches("^0[1-9]$|^[12][0-9]$|^30$")) {
				return true;
			}

			if (monthStatus == 3 && date[2].matches("^0[1-9]$|^[12][0-9]$|^3[01]$")) {
				return true;
			}

			return false;
		}
		return false;
	}

	// System.out.println("Enter notes date in format MM-DD-YYYY : ");
	// String date = sc.
	//
	public boolean matchesYear(String s) {
		Pattern yearPattern = Pattern.compile("^\\d{4}$");
		Matcher m = yearPattern.matcher(s);
		return m.find();
	}

	public int matchesMonth(String s) {
		Pattern monthPattern = Pattern.compile("0[1-9]|1[0-2]");
		Matcher m = monthPattern.matcher(s);
		if (!m.find()) {
			return 0;
		}
		if (s.equals("02")) {
			return 1;
		}
		if (s.equals("04") || s.equals("06") || s.equals("09") || s.equals("11")) {
			return 2;
		}

		return 3;
	}

	/*
	 * Divider is any non-digit symbol that separates the mounth, day and year in
	 * date string
	 */
	public static char findDivider(String dateString) {
		int i = 0;
		while (Character.isDigit(dateString.charAt(i)) && i < dateString.length()) {
			i++;
		}
		return dateString.charAt(i);
	}

}
