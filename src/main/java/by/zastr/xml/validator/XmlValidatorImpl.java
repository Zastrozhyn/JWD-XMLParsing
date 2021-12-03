package by.zastr.xml.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import by.zastr.xml.handler.DepositesErrorHandler;

public class XmlValidatorImpl implements XMLValidator {
	private static Logger logger = LogManager.getLogger();
    private static final String DEPOSITES_SCHEMA_LOCATION = "resources\\deposites.xsd";
    
    @Override
    public boolean isXMLValid(String fileName) {
    	boolean isValid=false;
    	String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    	SchemaFactory factory = SchemaFactory.newInstance(language);
    	File schemaLocation= new File(DEPOSITES_SCHEMA_LOCATION);
    	try {
    		Schema schema = factory.newSchema(schemaLocation);
    		Validator validator = schema.newValidator();
    		Source source = new StreamSource(fileName);
    		validator.setErrorHandler(new DepositesErrorHandler());
    		validator.validate(source);
    		isValid = true;
    } catch (SAXException e) {
        logger.log(Level.ERROR, "{} or {} is not correct of valid", fileName, DEPOSITES_SCHEMA_LOCATION);
    } catch (IOException e) {
        logger.log(Level.ERROR, "{} can't be read", fileName);
    }
    	return isValid;
    }

}
