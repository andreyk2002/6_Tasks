package by.epam_tc.sockets.archive.main;

import java.io.*;
import java.net.Socket;

import by.epam_tc.sockets.archive.client_logic.*;
import by.epam_tc.sockets.archive.entity.*;
import by.epam_tc.sockets.archive.exception.AccessDeniedException;

public class ArchiveMain {

	public static void main(String[] argc) {

		try (Socket socket = new Socket("127.0.0.1", 8000);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

			ClientFactory factory = ClientFactory.getInstance();
			User admin = factory.getAdmin("Admin_0");
			//Client client = factory.createClient("Client_0");

			ServerInteract connector = new ServerConnector(admin,socket);

			connector.viewPersons(writer, reader);
			connector.browseAll(writer, reader);
			connector.browseByName(writer, reader, "Igor Petrov");
			connector.browseByName(writer, reader, "This request give should nothing");
			connector.modifyCase(writer, reader, 9l, "gender : female, age : 19, student of Belarussian State University", true);
			//connector.createCase(writer, reader, "Victoria Andreeva", "gender : female, Age : 46"); 
			connector.browseAll(writer, reader);
			connector.closeConnection(writer);	//closing the socket
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AccessDeniedException e) {
			e.printStackTrace();
		}

	}

}
