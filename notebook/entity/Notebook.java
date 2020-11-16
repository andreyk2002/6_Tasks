package by.epam_tc.task_6.notebook.entity;


import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import by.epam_tc.task_6.notebook.dao.NoteWriter;

public class Notebook implements Serializable{
	
	private static final long serialVersionUID = 843323078268919972L;
	private List<Note> notes;

	public Notebook() {
		this.notes = new ArrayList<Note>();
	}

	public Notebook(List<Note> notes) {
		super();
		this.notes = notes;
	}

	public List<Note> getNotes() {
		return notes;
	}

	//создавать класс логики ради одного метода save считаю нерентабельным
	public void save() {
		NoteWriter writer = NoteWriter.getInstance();
		try {
			writer.saveNotes(this);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
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
		Notebook other = (Notebook) obj;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Notebook [notes=" + notes + "]";
	}
}
