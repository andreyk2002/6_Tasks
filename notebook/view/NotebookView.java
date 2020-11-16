package by.epam_tc.task_6.notebook.view;

import java.time.LocalDate;

import by.epam_tc.task_6.notebook.entity.*;
import by.epam_tc.task_6.notebook.logic.*;

public class NotebookView {

	private static NotebookView instance = null;

	private NotebookView() {

	}

	public static NotebookView getInstance() {
		if (instance == null) {
			instance = new NotebookView();
		}
		return instance;
	}
	
	public String getNoteInfo(Note note) {
		StringBuilder info = new StringBuilder();
		info.append("Theme : " + note.getTheme());
		info.append("\nDate : " + note.getCreateDate().toString());
		info.append("\nEmail : " + note.getEmail());
		info.append("\nMessage : " + note.getMessage());
		return info.toString();
	}

	public String getNotebookInfo(Notebook n) {
		StringBuilder info = new StringBuilder();
		for (Note note : n.getNotes()) {
			info.append(getNoteInfo(note));
			info.append("\n\n");
		}
		return info.toString();
	}

	public void printNotebookInfo(Notebook n) {
		System.out.println(getNotebookInfo(n));
	}

	public void showNotesByDate(LocalDate date, NoteSearch search) {
		Notebook notesByDate = search.findByDate(date);
		this.printNotebookInfo(notesByDate);
	}

	public void showNotesByEmail(String emailAddress, NoteSearch search) throws Exception  {

		Notebook notesByEmail = search.findByEmail(emailAddress);
		this.printNotebookInfo(notesByEmail);
		
	}

	public void showNotesByTheme(String theme, NoteSearch search) {
			Notebook notesByTheme = search.findByTheme(theme);
			this.printNotebookInfo(notesByTheme);
	}

	public void showNotesByWord(String word, NoteSearch search) {
		Notebook notesByWord = search.findByWord(word);
		this.printNotebookInfo(notesByWord);
	}
	
	public void showNotesByUserParam(NoteSearch search, String... paramsNames)  {

		try {
			Notebook notesByUserParams = search.findByUserParams(paramsNames);
			this.printNotebookInfo(notesByUserParams);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
