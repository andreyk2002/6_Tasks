package by.epam_tc.task_6.notebook.main;

import java.time.LocalDate;

import by.epam_tc.task_6.notebook.dao.NoteParser;
import by.epam_tc.task_6.notebook.entity.*;
import by.epam_tc.task_6.notebook.logic.*;
import by.epam_tc.task_6.notebook.view.NotebookView;

public class NotebookMain {

	public static Notebook getNotebook() {
		NoteParser parser = NoteParser.getInstance();
		Notebook notebook = new Notebook();
		try {
			notebook = parser.readNotes();
		} catch (Exception e) {
			System.out.println("Error during reading notes information from the file");
			System.out.println(e.getLocalizedMessage());
		}

		return notebook;
	}

	public static void main(String... argc) throws Exception {
		Notebook n = getNotebook();
		NoteSearch search = new NoteSearch(n);
		NotebookLogic logic = new NotebookLogic(n);
		NotebookView view = NotebookView.getInstance();
//		String newNoteText = "theme : Flight to Poland\n" + "date : 2020-10-08\n"
//				+ "email : p_1321dfuaodasdf@inbox.ru\n" + "message : Don't forget the documents!";
		try {
//			logic.addNote(newNoteText);

			view.showNotesByDate(LocalDate.of(2020, 8, 20), search);
			view.showNotesByEmail("andr@gmail.com", search);
			view.showNotesByTheme("Java programming", search);
			view.showNotesByWord("documents", search);
			view.showNotesByUserParam(search, "date", "email", "word");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		n.save();
	}

}
