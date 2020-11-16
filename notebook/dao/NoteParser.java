package by.epam_tc.task_6.notebook.dao;

import java.io.*;

import by.epam_tc.task_6.notebook.entity.Notebook;
import by.epam_tc.task_6.notebook.logic.NotebookLogic;

public class NoteParser {

	private static final String NOTES_PATH = "resources\\notes\\notes.txt";
	
	private static NoteParser instance = null;
	
	private NoteParser() {
		
	}
	
	public static NoteParser getInstance() {
		if(instance == null) {
			instance = new NoteParser();
		}
		return instance;
	}

	public Notebook readNotes() throws Exception {
		File notesFile = new File(NOTES_PATH);
		FileReader reader = new FileReader(notesFile);
		BufferedReader notesReader;
		notesReader = new BufferedReader(reader);
		StringBuilder text = new StringBuilder();
		String line;
		while ((line = notesReader.readLine()) != null) {
			text.append(line + "\n");
		}

		Notebook notebook = parseFromText(text.toString());
		notesReader.close();
		return notebook;
	}

	protected Notebook parseFromText(String text) {
		Notebook notebook = new Notebook();
		NotebookLogic logic = new NotebookLogic(notebook);
		String[] notesText = text.split("\n{2,}");

		for (String noteText : notesText) {
			try {
				logic.addNote(noteText);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		return notebook;
	}

}
