package by.epam.tc.oop.task_6.library.logic;

import java.io.IOException;

import by.epam.tc.oop.task_6.library.dao.UserIO;
import by.epam.tc.oop.task_6.library.entity.*;
import by.epam.tc.oop.task_6.library.view.ConsoleMenu;

public class LibraryLogic {
	private static LibraryLogic instance = null;

	private LibraryLogic() {

	}

	public static LibraryLogic getInstance() {
		if (instance == null) {
			instance = new LibraryLogic();
		}
		return instance;
	}

	public boolean findUserByName(Library l, String name) {
		for (User u : l.getUsers()) {
			if (u.getLogin().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean findUserByEmail(Library l, String emailName) {
		for (User u : l.getUsers()) {
			if (u.getEmail().getEmailAddress().equals(emailName)) {
				return true;
			}
		}
		return false;
	}

	public User createAccount(Library l, String login, String password, String emailAddress)
			throws IOException, IllegalArgumentException {
		if (findUserByName(l, login)) {
			System.out.println("Account with name " + login + " was already registered.");
			return null;
		}
		if (findUserByEmail(l, emailAddress)) {
			System.out.println("Email address " + emailAddress + " is already in use.");
			return null;
		}
		User newUser = new User(login, password, new Email(emailAddress));
		System.out.println("You was succesfully registered");
		l.getUsers().add(newUser);
		UserIO writer = UserIO.getInstance();
		writer.writeRegInfo(newUser);
		return newUser;
	}

	
	public User logIn(Library l, String login, String password) {
		for (User u : l.getUsers()) {
			if (u.getLogin().equals(login) && u.checkPassword(password)) {
				@SuppressWarnings("unused")
				ConsoleMenu menu = new ConsoleMenu(u);
				return u;
			}
		}
		System.out.println("Wrong password/login");
		return null;
	}
}
