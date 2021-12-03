package by.zastr.xml.builder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.zastr.xml.entity.AbstractDeposit;
import by.zastr.xml.entity.CurrentAccountDeposit;
import by.zastr.xml.entity.Depositor;
import by.zastr.xml.entity.RecurringDeposit;
import by.zastr.xml.entity.SavingDeposit;
import by.zastr.xml.exception.XmlDepositException;
import static by.zastr.xml.handler.DepositXmlTag.*;

public class DepositDomBuilder implements DepositBuilder{
    private static Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;
    private List<AbstractDeposit> depositList;
    
	public DepositDomBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        depositList= new ArrayList<AbstractDeposit>();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "DocumentBuilder cannot be created which satisfies the configuration requested");
        }
    }
	
    @Override
    public void buildListDeposit(String fileName) throws XmlDepositException {
    	Document doc;
    	try {
			doc = documentBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			
			NodeList currentAccountList=root.getElementsByTagName(CURRENT_ACCOUNT_DEPOSIT.toString());
			for (int i = 0; i < currentAccountList.getLength(); i++) {
				Element depositElement = (Element) currentAccountList.item(i);
	            AbstractDeposit deposit =  buildDeposit(depositElement);
	            depositList.add(deposit);
			}
			
			NodeList reccuringDepositList=root.getElementsByTagName(RECURRING_DEPOSIT.toString());
			for (int i = 0; i < reccuringDepositList.getLength(); i++) {
				Element depositElement = (Element) reccuringDepositList.item(i);
				AbstractDeposit deposit =  buildDeposit(depositElement);
	            depositList.add(deposit);
			}
			
			NodeList savingDepositList=root.getElementsByTagName(SAVING_DEPOSIT.toString());
			for (int i = 0; i < savingDepositList.getLength(); i++) {
				Element depositElement = (Element) savingDepositList.item(i);
				AbstractDeposit deposit =  buildDeposit(depositElement);
	            depositList.add(deposit);
			}
			
		} catch (SAXException e) {
			logger.log(Level.ERROR, "DocumentBuilder cannot be created which satisfies the configuration requested");
		} catch (IOException e) {
			logger.log(Level.ERROR, "Any SAX or IO Exception during parsing {}", fileName);
		}
    }
    
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return  node.getTextContent();
    }
    
    private AbstractDeposit buildDeposit (Element depositElement) throws XmlDepositException {
    	AbstractDeposit deposit;
    	if (depositElement.getTagName().equals(SAVING_DEPOSIT.toString())) {
    		deposit=new SavingDeposit();
    		String date=getElementTextContent(depositElement, EXPIRATION_DATE.toString());
    		((SavingDeposit)deposit).setDate(LocalDate.parse(date));
    	}
    	else if (depositElement.getTagName().equals(CURRENT_ACCOUNT_DEPOSIT.toString())) {
    		deposit=new CurrentAccountDeposit();
    		int credit=Integer.parseInt(getElementTextContent(depositElement, CREDIT_LIMIT.toString()));	
    		((CurrentAccountDeposit)deposit).setCreditLimit(credit);
    	}
    	else if (depositElement.getTagName().equals(RECURRING_DEPOSIT.toString())) {
    		deposit=new RecurringDeposit();    
    		int serviceCost=Integer.parseInt(getElementTextContent(depositElement, SERVICE_COST.toString()));
    		((RecurringDeposit)deposit).setServiceCost(serviceCost);
    	}
        else {
        	throw new XmlDepositException("Invalid deposit type");
        } 
        
        String id=getElementTextContent(depositElement, ACCOUNT_ID.toString());
        deposit.setAccountId(id);
        String bank=getElementTextContent(depositElement, BANK_NAME.toString());
        deposit.setBankName(bank);
        String country=getElementTextContent(depositElement, COUNTRY.toString());
        deposit.setCountry(country);
        long amount= Long.parseLong(getElementTextContent(depositElement, AMOUNT_ON_DEPOSIT.toString()));
        deposit.setAmountOnDeposit(amount);
        byte profitability=Byte.parseByte(getElementTextContent(depositElement, PROFITABILITY.toString()));
        deposit.setProfitobility(profitability);
        NodeList depositorNode=depositElement.getElementsByTagName(DEPOSITOR.toString());
        Element depositorElement=(Element)depositorNode.item(0);
        String name=getElementTextContent(depositorElement, NAME.toString());
        byte age=Byte.parseByte(getElementTextContent(depositorElement, AGE.toString()));
        String phone=getElementTextContent(depositorElement, PHONE.toString());
        Depositor depositor=new Depositor(name, age, phone);
        deposit.setDepositor(depositor);
        String website = depositElement.getAttribute(WEBSITE.toString());
        if (website.isBlank()) {
            website = AbstractDeposit.NO_WEBSITE;
        }
        deposit.setWebsite(website);
        boolean revocable=Boolean.parseBoolean(depositElement.getAttribute(REVOCABLE.toString()));
        deposit.setRevocable(revocable);
        logger.log(Level.INFO, "build completed successfully");
    	return deposit;
    }
    
    @Override
    public List<AbstractDeposit> getDepositList() {
		return depositList;
	}
    
}
