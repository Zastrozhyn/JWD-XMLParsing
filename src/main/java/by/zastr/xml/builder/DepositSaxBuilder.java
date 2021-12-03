package by.zastr.xml.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import by.zastr.xml.entity.AbstractDeposit;
import by.zastr.xml.exception.XmlDepositException;
import by.zastr.xml.handler.DepositHandler;
import by.zastr.xml.validator.XMLValidator;
import by.zastr.xml.validator.XmlValidatorImpl;

public class DepositSaxBuilder implements DepositBuilder {
	private List<AbstractDeposit> depositeList;
	private static Logger logger = LogManager.getLogger();
	private DepositHandler handler = new DepositHandler();
    private XMLReader reader;
    private XMLValidator validator=new XmlValidatorImpl();
	
	public DepositSaxBuilder() {
		depositeList=new ArrayList<AbstractDeposit>();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser cannot be created which satisfies the requested configuration");
        } catch (SAXException e) {
            logger.log(Level.ERROR, "any SAX errors occur during processing");
        }

        reader.setContentHandler(handler);
	}

	@Override
	public List<AbstractDeposit> getDepositList() {
		return depositeList;
	}

	@Override
	public void buildListDeposit(String fileName) throws XmlDepositException {
        if (!validator.isXMLValid(fileName)) {
            throw new XmlDepositException(String.format("File %s hasn't passed validation!", fileName));
        }

        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, "Any SAX or IO Exception during parsing {}", fileName);
        }

        depositeList = handler.getDepositeList();
		
	}
	

}
