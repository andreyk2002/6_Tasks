package by.epam_tc.sockets.archive.client_logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import by.epam_tc.sockets.archive.exception.AccessDeniedException;

public interface ServerInteract {
	void closeConnection(BufferedWriter writer);

	void save(BufferedWriter writer, BufferedReader reader) throws AccessDeniedException, IOException;

	void viewPersons(BufferedWriter writer, BufferedReader reader) throws IOException;

	void browseByName(BufferedWriter writer, BufferedReader reader, String person) throws AccessDeniedException, IOException;

	void browseAll(BufferedWriter writer, BufferedReader reader) throws AccessDeniedException, IOException;

	void findCase(BufferedWriter writer, BufferedReader reader, Long id) throws AccessDeniedException, IOException;

	void createCase(BufferedWriter writer, BufferedReader reader, String newCasePerson, String newCaseContent)
			throws AccessDeniedException, IOException;

	void modifyCase(BufferedWriter writer, BufferedReader reader, Long caseId, String newContent,
			boolean deletePreviousContent) throws AccessDeniedException, IOException;
}
