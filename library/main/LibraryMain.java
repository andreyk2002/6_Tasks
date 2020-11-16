package by.epam.tc.oop.task_6.library.main;

import java.io.IOException;

import by.epam.tc.oop.task_6.library.entity.*;
import by.epam.tc.oop.task_6.library.logic.LibraryLogic;

public class LibraryMain {

	public static void main(String... argc) {
		Library l = null;
		try {
			l = new Library();
		} catch (IOException e1) {
			
			e1.printStackTrace();
			System.exit(1);
		}
		LibraryLogic libLogic = LibraryLogic.getInstance();
		try {
			libLogic.createAccount(l, "fyodarSuper222", "00112233", "fedot1@mail.ru");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		libLogic.logIn(l, "admin1", "00151782333");
		libLogic.logIn(l, "fyodarSuper222", "00112233");
		libLogic.logIn(l, "admin1", "00151782333");

	}
}
