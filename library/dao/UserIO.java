package by.epam.tc.oop.task_6.library.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import by.epam.tc.oop.task_6.library.entity.*;

public class UserIO {
	private final static short encoder = 20208;
	private static final String REGISTRATION_INFO_PATH = "resources\\library\\reg.txt";
	private static UserIO instance = null;
	
	private UserIO(){
		
	}
	
	public static UserIO getInstance() {
		if(instance == null) {
			instance = new UserIO();
		}
		return instance;
	}

	public void writeRegInfo(User u) throws IOException {
		FileWriter writer = new FileWriter(REGISTRATION_INFO_PATH, true);
		writer.append(u.getInfo());
		writer.close();
	}

	public ArrayList<User> getUsers() throws IOException {
		ArrayList<User> users = new ArrayList<User>();
		BufferedReader reader = new BufferedReader(new FileReader(REGISTRATION_INFO_PATH));

		String line;
		while ((line = reader.readLine()) != null) {
			User u = parseFromString(line);

			users.add(u);
		}
		reader.close();
		return users;
	}

	protected static User parseFromString(String line) throws IOException {
		String login;
		String encodedPassword;//什仁什仁什仁什仁什
		String emailAddress;
		int i = 0;
		String[]properties = line.split(",\\s");
		login = properties[i++];
		encodedPassword = properties[i++];
		StringBuilder password = new StringBuilder();
		for (int j = 0; j < encodedPassword.length(); j++) {
			char c = (char) (encodedPassword.charAt(j) ^ encoder);
			password.append(c);
		}
		
		emailAddress = properties[i++];
		if (properties[i].equalsIgnoreCase("admin")) {
			return new Admin(login, password.toString(), new Email(emailAddress));
		}
		return new User(login, password.toString(), new Email(emailAddress));
	}

}
