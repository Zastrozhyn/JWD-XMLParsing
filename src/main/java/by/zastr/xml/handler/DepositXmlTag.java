package by.zastr.xml.handler;

public enum DepositXmlTag {
	DEPOSITOR,
	DEPOSITES,
	ACCOUNT_ID,
	COUNTRY,
	BANK_NAME,
	NAME,
	AGE,
	PHONE,
	AMOUNT_ON_DEPOSIT,
	PROFITABILITY,
	EXPIRATION_DATE,
	SERVICE_COST,
	CREDIT_LIMIT,
	WEBSITE,
	REVOCABLE,
	CURRENT_ACCOUNT_DEPOSIT,
	RECURRING_DEPOSIT,
	SAVING_DEPOSIT;
	
	public String toString() {
		return this.name().toLowerCase().replace("_", "-");
	}
}
