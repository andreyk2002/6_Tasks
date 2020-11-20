package by.epam_tc.sockets.archive.server_logic;

import javax.xml.parsers.*;
import java.io.IOException;
import org.xml.sax.*;

import by.epam_tc.sockets.archive.server.ServerCommand;

public class ClientRequestHandler {
	public static final String QUIT_COMMAND = "QUIT";
	public static final String XML_PATH = "resources\\archive\\archive.xml";
	private XmlHandle xmlHandler;

	public ClientRequestHandler(String path) throws SAXException, IOException, ParserConfigurationException {
		xmlHandler = new XmlHandler(path);
	}

	public ClientRequestHandler() throws SAXException, IOException, ParserConfigurationException {
		this(XML_PATH);
	}

	public String handleRequest(String command) throws Exception {
		String[] clientReq = command.split(";");
		switch (ServerCommand.valueOf(clientReq[0])) {
		case BROWSE_BY_NAME:
			return xmlHandler.browseByName(clientReq);
		case BROWSE_ALL:
			return xmlHandler.browseAll(clientReq);
		case FIND:
			return xmlHandler.find(clientReq);
		case MODIFY:
			return xmlHandler.modify(clientReq);
		case VIEW_PERSONS:
			return xmlHandler.getDocumentsPersons();
		case CREATE:
			return xmlHandler.create(clientReq);
		case SAVE:
			return xmlHandler.save();
		case QUIT:
			return xmlHandler.quit();

		}
		return null;
	}

}
