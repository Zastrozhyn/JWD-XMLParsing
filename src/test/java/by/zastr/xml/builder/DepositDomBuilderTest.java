package by.zastr.xml.builder;

import org.testng.annotations.Test;

import by.zastr.xml.exception.XmlDepositException;

public class DepositDomBuilderTest {
	DepositDomBuilder domBuilder = new DepositDomBuilder();

	  @Test(expectedExceptions = XmlDepositException.class)
	  public void buildListDepositTest() throws XmlDepositException {
	    domBuilder.buildDepositList("resources\\invalidDeposites.xml");
	  }
}
