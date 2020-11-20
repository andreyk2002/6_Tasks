package by.epam_tc.sockets.archive.server_logic;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import by.epam_tc.sockets.archive.entity.Case;

public class XmlHandler implements XmlHandle {
	private Document doc = null;
	private XPath xpath;
	

	public XmlHandler(String path) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder;
		builder = domFactory.newDocumentBuilder();
		doc = builder.parse(path);
		xpath = XPathFactory.newInstance().newXPath();
	}

	@Override
	public String getDocumentsPersons() throws XPathExpressionException {
		StringBuilder allDocsPersons = new StringBuilder();// Case [person=" + person + ", content=" + content + "]
		NodeList nameNodes = getNodeList("//archive//case//person//text()");

		for (int i = 0; i < nameNodes.getLength(); i++) {
			allDocsPersons.append("Case [person = ");
			allDocsPersons.append(nameNodes.item(i).getNodeValue());
			allDocsPersons.append(" ]\n");
		}

		return allDocsPersons.toString();
	}

	@Override
	public String browseByName(String[] clientReq) throws XPathExpressionException {
		String name = clientReq[1];
		StringBuilder doc = new StringBuilder();
		NodeList nameNodes = getNodeList("//archive//case//person//text()");
		NodeList infoNodes = getNodeList("//archive//case//info//text()");
		NodeList caseNodes = getNodeList("//archive//case");
		for (int i = 0; i < nameNodes.getLength(); i++) {
			if (nameNodes.item(i).getNodeValue().equalsIgnoreCase(name)) {
				doc.append(NodeToCaseString(caseNodes.item(i), nameNodes.item(i), infoNodes.item(i)));
			}
		}
		return doc.length() == 0 ? "Can't find Case with person " + name : doc.toString();

	}

	@Override
	public String browseAll(String[] clientReq) throws XPathExpressionException {
		StringBuilder allDocs = new StringBuilder();// Case [person=" + person + ", content=" + content + "]
		NodeList nameNodes = getNodeList("//archive//case//person//text()");
		NodeList caseNodes = getNodeList("//archive//case");
		NodeList infoNodes = getNodeList("//archive//case//info//text()");
		for (int i = 0; i < caseNodes.getLength(); i++) {
			allDocs.append(NodeToCaseString(caseNodes.item(i), nameNodes.item(i), infoNodes.item(i)));
		}
		return allDocs.toString();
	}

	@Override
	public String create(String[] req) throws Exception {
		Long newId;
		newId = ((Number)xpath.evaluate("count(//archive//case)", doc, XPathConstants.NUMBER)).longValue() + 1;
		Case document = new Case(req[1], req[2], newId);
	
		
		Node archiveNode = getNode("//archive");
	    Element emp = null;
	        
	    emp = (Element) archiveNode;
	    Element caseEl = doc.createElement("case");
	    caseEl.setAttribute("id", newId.toString());
	    
	    Element person = doc.createElement("person");
	    person.appendChild(doc.createTextNode(document.getPerson()));
	    
	    Element info = doc.createElement("info");
	    info.appendChild(doc.createTextNode(document.getContent()));
	    
	    caseEl.appendChild(person);
	    caseEl.appendChild(info);
	    
	    emp.appendChild(caseEl);

		return "New case was succesfully created";
	}

	@Override
	public String modify(String[] req) throws XPathExpressionException {
		StringBuilder exprPattern = new StringBuilder("//archive//case[@id = '");
		exprPattern.append(req[1]).append("']");
		StringBuilder expInfo = new StringBuilder(exprPattern);
		
		Node res = getNode(exprPattern.toString());
		Node resInfo = getNode(expInfo.append("//info//text()").toString());
		
		String content = req[2];
		boolean shouldDelete = Boolean.parseBoolean(req[3]);
		if(res == null) {
			return "can't find case with id " + req[1];
		}
		if(shouldDelete) {
			resInfo.setNodeValue(content);
		} else {
			resInfo.setNodeValue(resInfo.getNodeValue() + " " + content);
		}
		return "The modification was successful";
	
	}

	@Override
	public String find(String[] req) throws XPathExpressionException {
		StringBuilder exprPattern = new StringBuilder("//archive//case[@id = '");
		exprPattern.append(req[1]).append("']");
		StringBuilder expInfo = new StringBuilder(exprPattern);
		StringBuilder expName =  new StringBuilder(exprPattern);
		Node res = getNode(exprPattern.toString());
		Node resInfo = getNode(expInfo.append("//info//text()").toString());
		Node resName = getNode(expName.append("//person//text()").toString());

		StringBuilder result = new StringBuilder();
		result.append(NodeToCaseString(res,  resName, resInfo));
		return result.toString();
	}

	private NodeList getNodeList(String exprPattern) throws XPathExpressionException {
		XPathExpression expr;
		Object result = null;
		expr = xpath.compile(exprPattern);
		result = expr.evaluate(doc, XPathConstants.NODESET);

		return (NodeList) result;
	}

	private Node getNode(String exprPattern) throws XPathExpressionException {
		XPathExpression expr;
		expr = xpath.compile(exprPattern);
		Node res = (Node) expr.evaluate(doc, XPathConstants.NODE);
		return res;
	}
		
	private String NodeToCaseString(Node caseNode, Node nameNode, Node infoNode) {

		StringBuilder doc = new StringBuilder();
		doc.append("Case ");
		doc.append(caseNode.getAttributes().item(0));
		doc.append(" [person = ");
		doc.append(nameNode.getNodeValue());
		doc.append(", content = ");
		doc.append((infoNode.getNodeValue().replaceAll("\\s+", " ")));
		doc.append(" ]\n");
		return doc.toString();
	}

	@Override
	public String save() throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(ClientRequestHandler.XML_PATH));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        return ("XML file updated successfully");   
	}

	@Override
	public String quit() {
		return ClientRequestHandler.QUIT_COMMAND;
	}
}
