package by.zastr.xml.validator;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XmlValidatorImplTest {
	XmlValidatorImpl validator;
	
	@BeforeClass
	public void setUp() {
		validator= new XmlValidatorImpl();
	}
	
    @Test
    public void isXMLValidTest() {
    	boolean actual = true;
    	boolean expected = validator.isXMLValid("resources\\deposites.xml");
    	assertEquals(actual, expected);
    }
    @Test
    public void isXMLValidTestFalse() {
    	boolean actual = false;
    	boolean expected = validator.isXMLValid("resources\\invalidDeposites.xml");
    	assertEquals(actual, expected);
    }
}
