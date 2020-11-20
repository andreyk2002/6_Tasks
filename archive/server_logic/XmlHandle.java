package by.epam_tc.sockets.archive.server_logic;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

public interface XmlHandle {

	String getDocumentsPersons() throws XPathExpressionException;

	String browseByName(String[] clientReq) throws XPathExpressionException;

	String browseAll(String[] clientReq) throws XPathExpressionException;
	
	String create(String[] req) throws XPathExpressionException, Exception;

	String modify(String[] req) throws XPathExpressionException;
	
	String find(String[]req) throws XPathExpressionException;
	
	String save() throws TransformerException;

	String quit();

}
