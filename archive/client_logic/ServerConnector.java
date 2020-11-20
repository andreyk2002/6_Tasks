package by.epam_tc.sockets.archive.client_logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import by.epam_tc.sockets.archive.entity.*;
import by.epam_tc.sockets.archive.exception.AccessDeniedException;
import by.epam_tc.sockets.archive.server.ServerCommand;

public class ServerConnector implements ServerInteract {

	private User user;
	private final Socket connectedSocket;

	public ServerConnector(User cl, Socket socket) {
		this.user = cl;
		connectedSocket = socket;
	}

	public ServerConnector(Socket socket) {
		this.user = new Guest();
		connectedSocket = socket;
	}

	public void changeUser(User anotherClient) {
		user = anotherClient;
	}

	private static void writeRequest(BufferedWriter writer, String request) {
		try {
			writer.write(request);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void handleResponse(BufferedReader reader) throws IOException {
		boolean stop = false;
		while (connectedSocket.isConnected()) {
			while (reader.ready()) {
				System.out.println(reader.readLine());
				stop = true;
			}
			if (stop) {
				return;
			}
		}
	}

	@Override
	public void closeConnection(BufferedWriter writer) {
		writeRequest(writer, ServerCommand.QUIT.toString());
	}

	@Override
	public void save(BufferedWriter writer, BufferedReader reader) throws AccessDeniedException, IOException {
		if (!(user instanceof Admin)) {
			closeConnection(writer);
			throw new AccessDeniedException("Only administrators can modify information in the archive");
		}
		writeRequest(writer, ServerCommand.SAVE.toString());
		handleResponse(reader);
	}

	@Override
	public void viewPersons(BufferedWriter writer, BufferedReader reader) throws IOException {
		writeRequest(writer, ServerCommand.VIEW_PERSONS.toString());
		handleResponse(reader);
	}

	@Override
	public void browseByName(BufferedWriter writer, BufferedReader reader, String person)
			throws AccessDeniedException, IOException {
		if (user instanceof Guest) {
			closeConnection(writer);
			throw new AccessDeniedException("Only administrators can modify information in the archive");
		}
		StringBuilder request = new StringBuilder();
		request.append(ServerCommand.BROWSE_BY_NAME).append(";").append(person);
		writeRequest(writer, request.toString());
		handleResponse(reader);
	}

	@Override
	public void browseAll(BufferedWriter writer, BufferedReader reader) throws AccessDeniedException, IOException {
		if (user instanceof Guest) {
			closeConnection(writer);
			throw new AccessDeniedException("Only administrators can modify information in the archive");
		}
		writeRequest(writer, ServerCommand.BROWSE_ALL.toString());
		handleResponse(reader);
	}

	@Override
	public void findCase(BufferedWriter writer, BufferedReader reader, Long id)
			throws AccessDeniedException, IOException {
		StringBuilder request = new StringBuilder();
		if (user instanceof Guest) {
			closeConnection(writer);
			throw new AccessDeniedException("Only administrators can modify information in the archive");
		}
		request.append(ServerCommand.FIND).append(";");
		request.append(id);
		writeRequest(writer, request.toString());
		handleResponse(reader);
	}

	@Override
	public void createCase(BufferedWriter writer, BufferedReader reader, String newCasePerson, String newCaseContent)
			throws AccessDeniedException, IOException {
		if (!(user instanceof Admin)) {
			closeConnection(writer);
			throw new AccessDeniedException("Only administrators can modify information in the archive");
		}

		StringBuilder request = new StringBuilder();
		request.append(ServerCommand.CREATE).append(";").append(newCasePerson);
		request.append(";").append(newCaseContent);
		writeRequest(writer, request.toString());
		handleResponse(reader);
		save(writer, reader);
	}

	@Override
	public void modifyCase(BufferedWriter writer, BufferedReader reader, Long caseId, String newContent,
			boolean deletePreviousContent) throws AccessDeniedException, IOException {
		if (!(user instanceof Admin)) {
			closeConnection(writer);
			throw new AccessDeniedException("Only administrators can modify information in the archive");

		}

		StringBuilder request = new StringBuilder();
		request.append(ServerCommand.MODIFY).append(";");
		request.append(caseId).append(";");
		request.append(newContent).append(";");
		request.append(deletePreviousContent);
		writeRequest(writer, request.toString());
		handleResponse(reader);
		save(writer, reader);
	}

}
