package by.zastr.xml.handler;

public enum DepositXmlTag {
	BANK_NAME("bank-name"),
	COUNTRY("country"),
	DEPOSITOR("depositor"),
	NAME("name"),
	AGE("age"),
	PHONE("phone"),
	ACCOUNT_ID("account-id"),
	AMMOUNT_ON_DEPOSIT("ammount-on-deposit"),
	PROFITABILITY("profitability"),
	EXPIRATION_DATE("expiration-date"),
	SERVICE_COST("service-cost"),
	WEBSITE("website"),
	CREDIT_LIMIT("credit-limit");
	private String value;
	
	DepositXmlTag(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
}
