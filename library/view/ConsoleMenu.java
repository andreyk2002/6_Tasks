package by.epam.tc.oop.task_6.library.view;

import by.epam.tc.oop.task_6.library.action.ConsoleMenuAction;
import by.epam.tc.oop.task_6.library.entity.*;


public class ConsoleMenu {

	private final int USER_ACTIONS_AMOUNT = 5;
	private final int ADMIN_ACTIONS_AMOUNT = 9;

	private ConsoleMenuAction action = ConsoleMenuAction.getInstance();

	public ConsoleMenu(User u) {
		showActions(u);
		if (u instanceof Admin) {
			action.choseAction(u, ADMIN_ACTIONS_AMOUNT);
		} else {
			action.choseAction(u, USER_ACTIONS_AMOUNT);
		}

	}
	
	public ConsoleMenu() {
		System.out.println("You was entered like a guest");
		User guest = new User();
		showActions(guest);
		action.choseAction(guest, USER_ACTIONS_AMOUNT);
	}

	public void showActions(User u) {
		if (u instanceof Admin) {
			showAdminActions();
			return;
		}
		showUserActions();
	}

	private void showUserActions() {
		System.out.println("Enter \"0\" for quit");
		System.out.println("Enter \"1\" for browsing book catalog");
		System.out.println("Enter \"2\" for searching book by name in catalog");
		System.out.println("Enter \"3\" for searching books by author name in catalog");
		System.out.println("Enter \"4\" for viewing emails");
		System.out.println("Enter \"5\" for sending email to administrator");
	}

	private void showAdminActions() {
		showUserActions();
		System.out.println("Enter \"6\" for deleting book by name from catalog");
		System.out.println("Enter \"7\" for deleting book by author name from catalog");
		System.out.println("Enter \"8\" for adding book in catalog");
		System.out.println("Enter \"9\" for modifyning book description");
	}
}
