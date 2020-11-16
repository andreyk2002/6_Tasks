package by.epam_tc.task_6.notebook.dao;

import java.io.*;

import by.epam_tc.task_6.notebook.entity.Notebook;
import by.epam_tc.task_6.notebook.view.*;

public class NoteWriter {

	private static NoteWriter instance = null;

	private NoteWriter() {

	}

	public static NoteWriter getInstance() {
		if (instance == null) {
			instance = new NoteWriter();
		}
		return instance;
	}

	private static final String NOTES_PATH = "resources\\notes\\notes.txt";

	public void saveNotes(Notebook n) throws IOException {
		File notesFile = new File(NOTES_PATH);
		FileWriter writer = new FileWriter(notesFile);
		NotebookView view = NotebookView.getInstance();
		writer.write(view.getNotebookInfo(n));
		writer.close();
	}

}
