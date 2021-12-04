package by.zastr.xml.builder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;

import by.zastr.xml.entity.AbstractDeposit;
import by.zastr.xml.entity.CurrentAccountDeposit;
import by.zastr.xml.entity.RecurringDeposit;
import by.zastr.xml.entity.SavingDeposit;
import by.zastr.xml.exception.XmlDepositException;
import by.zastr.xml.handler.DepositXmlTag;
import by.zastr.xml.validator.XMLValidator;
import by.zastr.xml.validator.XmlValidatorImpl;

import static by.zastr.xml.handler.DepositXmlTag.*;
import javax.xml.stream.XMLInputFactory;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class DepositStaxBuilder implements DepositBuilder{
	
    private static Logger logger = LogManager.getLogger();
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";
    private List<AbstractDeposit> depositList;
    private XMLInputFactory inputFactory;
    private AbstractDeposit currentDeposit;
    private XMLValidator validator = new XmlValidatorImpl();
    
    public DepositStaxBuilder() {
        inputFactory = XMLInputFactory.newFactory();
        depositList = new ArrayList<>();
    }

	@Override
	public List<AbstractDeposit> getDepositList() {
		return depositList;
	}

	@Override
	public void buildDepositList(String fileName) throws XmlDepositException {
		if (!validator.isXMLValid(fileName)) {
            throw new XmlDepositException(String.format("File %s hasn't passed validation!", fileName));
        }
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(CURRENT_ACCOUNT_DEPOSIT.toString())
                            || name.equals(RECURRING_DEPOSIT.toString())
                            || name.equals(SAVING_DEPOSIT.toString())) {
                        AbstractDeposit deposit = buildDeposit(reader);
                        depositList.add(deposit);
                    }
                }
            }
        } catch (XMLStreamException e) {
        	logger.log(Level.ERROR, "File %s can't be parsed".formatted(fileName), e);
            throw new XmlDepositException("File %s can't be parsed".formatted(fileName), e);
        } catch (FileNotFoundException e) {
        	logger.log(Level.ERROR, "File %s not found".formatted(fileName), e);
            throw new XmlDepositException("File %s not found".formatted(fileName), e);
        } catch (IOException e) {
        	logger.log(Level.ERROR, "File %s can't be read".formatted(fileName), e);
            throw new XmlDepositException("File %s can't be read".formatted(fileName), e);
        }
        logger.log(Level.INFO, "STAX parsing deposites has finished successfully");
    }
		
	private AbstractDeposit buildDeposit(XMLStreamReader reader) throws XMLStreamException {
        DepositXmlTag currentXmlTag = valueOf(reader.getLocalName().toUpperCase().replace(HYPHEN, UNDERSCORE));
    	switch (currentXmlTag) {
        case CURRENT_ACCOUNT_DEPOSIT:
            currentDeposit = new CurrentAccountDeposit();
            break;
        case RECURRING_DEPOSIT:
            currentDeposit = new RecurringDeposit();
            break;
        case SAVING_DEPOSIT:
            currentDeposit = new SavingDeposit();
            break;    
        }
        
        boolean revocable = Boolean.parseBoolean(reader.getAttributeValue(null, REVOCABLE.toString()));
        currentDeposit.setRevocable(revocable);
        String webSite = reader.getAttributeValue(null, WEBSITE.toString());
        if (webSite != null) {
            currentDeposit.setWebsite(webSite);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
            	case XMLStreamConstants.START_ELEMENT:
            		name = reader.getLocalName();  
            		String data= getXmlText(reader);
            		DepositXmlTag xmlTag = DepositXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
            		switch (xmlTag) {
	            		case ACCOUNT_ID:
	                        currentDeposit.setAccountId(data);
	                        break;
	                    case BANK_NAME:
	                        currentDeposit.setBankName(data);
	                        break;
	                    case COUNTRY:
	                        currentDeposit.setCountry(data);
	                        break;
	                    case AMOUNT_ON_DEPOSIT:
	                    	long amount = Long.parseLong(data);
	                        currentDeposit.setAmountOnDeposit(amount);
	                        break; 
	                    case PROFITABILITY:
	                    	byte profitability = Byte.parseByte(data);
	                        currentDeposit.setProfitobility(profitability);
	                        break; 
	                    case NAME:
	                        currentDeposit.getDepositor().setName(data);
	                        break; 
	                    case AGE:
	                    	byte age=Byte.parseByte(data);
	                        currentDeposit.getDepositor().setAge(age);
	                        break; 
	                    case PHONE:
	                        currentDeposit.getDepositor().setPhone(data);
	                        break; 
	                    case EXPIRATION_DATE:                	
	                        ((SavingDeposit)currentDeposit).setDate(LocalDate.parse(data));
	                        break;
	                    case CREDIT_LIMIT:  
	                    	long creditLimit = Long.parseLong(data);
	                        ((CurrentAccountDeposit)currentDeposit).setCreditLimit(creditLimit);;
	                        break;
	                    case SERVICE_COST:  
	                    	int cost = Integer.parseInt(data);
	                        ((RecurringDeposit)currentDeposit).setServiceCost(cost);		 
            		}        
                    break;
            	case XMLStreamConstants.END_ELEMENT:
            		name = reader.getLocalName();
            		if (DepositXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)) ==CURRENT_ACCOUNT_DEPOSIT
            				|| DepositXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)) ==RECURRING_DEPOSIT
            				|| DepositXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)) ==SAVING_DEPOSIT) {
            			return currentDeposit;
            		}            
            }
        }
        logger.log(Level.ERROR, "Unknown element in Deposites tags");
        throw new XMLStreamException("Unknown element in Deposites tags"); 
    }
	
	private String getXmlText (XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}

}
