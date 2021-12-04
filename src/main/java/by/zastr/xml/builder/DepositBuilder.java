package by.zastr.xml.builder;

import java.util.List;

import by.zastr.xml.entity.AbstractDeposit;
import by.zastr.xml.exception.XmlDepositException;

public interface DepositBuilder {

	List<AbstractDeposit> getDepositList();

	void buildDepositList(String fileName) throws XmlDepositException;

}