package by.zastr.xml.entity;

public class RecurringDeposit extends Deposit{
	public RecurringDeposit(String bankName, String country, Depositor depositor, String accountId,
			long amountOnDeposit, byte profitobility) {
		super(bankName, country, depositor, accountId, amountOnDeposit, profitobility);
	}

	private int serviceCost;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + serviceCost;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecurringDeposit other = (RecurringDeposit) obj;
		if (serviceCost != other.serviceCost)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("RecurringDeposit [serviceCost=");
		builder.append(serviceCost);
		builder.append("]");
		return builder.toString();
	}
	
	

}
