package by.zastr.xml.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DepositesErrorHandler implements ErrorHandler {
	private static Logger logger=LogManager.getLogger();
	private static final String MESSAGE = "{} : {} - {}";
	public void warning(SAXParseException exception) throws SAXException {
		logger.warn(MESSAGE, exception.getLineNumber(), exception.getColumnNumber(), exception.getMessage());
        throw new SAXException();
		
	}
	public void error(SAXParseException exception) throws SAXException {
		logger.error(MESSAGE, exception.getLineNumber(), exception.getColumnNumber(), exception.getMessage());
        throw new SAXException();
	}
	public void fatalError(SAXParseException exception) throws SAXException {
		logger.fatal(MESSAGE, exception.getLineNumber(), exception.getColumnNumber(), exception.getMessage());
        throw new SAXException();
	}
	  
	
}
