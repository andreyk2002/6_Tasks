package by.epam_tc.sockets.archive.server;

import java.io.*;
import java.net.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import by.epam_tc.sockets.archive.server_logic.ClientRequestHandler;

public class Server {

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(8000)) {

		 	while (true) {
				try (Socket socket = server.accept();
						BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
						BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
					ClientRequestHandler clientHandler = null;
					try {
						clientHandler = new ClientRequestHandler();
					} catch (SAXException | ParserConfigurationException e1) {
						e1.printStackTrace();
						System.exit(1);
					}
					while (socket.isConnected()) {
						String request = reader.readLine();
						String response = clientHandler.handleRequest(request);
						
						writer.write(response);
						writer.newLine();
						writer.flush();
						if(response.equals(ClientRequestHandler.QUIT_COMMAND)) {
							socket.close();
							break;
						}
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.err.println(e.getLocalizedMessage());
				}
			}
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
}
