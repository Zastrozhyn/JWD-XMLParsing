package by.zastr.xml.builder;

import org.testng.annotations.Test;

import by.zastr.xml.exception.XmlDepositException;

public class DepositStaxBuilderTest {
	
	DepositStaxBuilder staxBuilder= new DepositStaxBuilder();

  @Test(expectedExceptions = XmlDepositException.class)
  public void buildListDepositTest() throws XmlDepositException {
    staxBuilder.buildDepositList("resources\\invalidDeposites.xml");
  }
}
