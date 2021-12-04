package by.zastr.xml.handler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import static by.zastr.xml.handler.DepositXmlTag.*;
import by.zastr.xml.entity.AbstractDeposit;
import by.zastr.xml.entity.CurrentAccountDeposit;
import by.zastr.xml.entity.RecurringDeposit;
import by.zastr.xml.entity.SavingDeposit;

public class DepositHandler extends DefaultHandler{
	private List<AbstractDeposit> depositeList;
	private AbstractDeposit currentDeposit;
	private DepositXmlTag currentXmlTag;
	private EnumSet<DepositXmlTag> withText;
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private static Logger logger = LogManager.getLogger();
	
	public DepositHandler() {
		depositeList = new ArrayList<AbstractDeposit>();
		withText = EnumSet.range(DepositXmlTag.ACCOUNT_ID, DepositXmlTag.WEBSITE);
	}
	
	public List<AbstractDeposit> getDepositeList() {
		return depositeList;
	}
	
	@Override
	public void startElement (String uri, String localName, String qName, Attributes attributes) {
		if (qName.equals(CURRENT_ACCOUNT_DEPOSIT.toString())
                || qName.equals(RECURRING_DEPOSIT.toString())
                || qName.equals(SAVING_DEPOSIT.toString())) {
        	currentXmlTag = valueOf(qName.toUpperCase().replace(HYPHEN, UNDERSCORE));
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
            currentXmlTag = null;
            currentDeposit.setRevocable(Boolean.valueOf(attributes.getValue(REVOCABLE.toString())) );

            if (attributes.getLength() == 2) {
                currentDeposit.setWebsite(attributes.getValue(WEBSITE.toString()));
            }
        }
        else {
            DepositXmlTag temp = valueOf(qName.toUpperCase().replace(HYPHEN, UNDERSCORE));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }   
	}
	
	@Override
    public void endElement(String uri, String localName, String qName) {
		if (qName.equals(CURRENT_ACCOUNT_DEPOSIT.toString())
                || qName.equals(RECURRING_DEPOSIT.toString())
                || qName.equals(SAVING_DEPOSIT.toString())) {
            depositeList.add(currentDeposit);
            currentDeposit = null;
        }
    }
	
	@Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
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
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
                    }
            }
            currentXmlTag = null;
        }
	
    @Override
    public void endDocument() {
        logger.log(Level.INFO, "SAX parsing deposites has finished successfully");
    }
	
}
