package by.zastr.xml.entity;

import java.time.LocalDate;

public class CurrentAccountDeposit extends Deposit{
	public CurrentAccountDeposit(String bankName, String country, Depositor depositor, String accountId,
			long amountOnDeposit, byte profitobility) {
		super(bankName, country, depositor, accountId, amountOnDeposit, profitobility);
	}

	private LocalDate experationDate;
	private long creditLimit;

	

	public LocalDate getExperationDate() {
		return experationDate;
	}

	public void setExperationDate(LocalDate experationDate) {
		this.experationDate = experationDate;
	}

	public long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(long creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (creditLimit ^ (creditLimit >>> 32));
		result = prime * result + ((experationDate == null) ? 0 : experationDate.hashCode());
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
		CurrentAccountDeposit other = (CurrentAccountDeposit) obj;
		if (creditLimit != other.creditLimit)
			return false;
		if (experationDate == null) {
			if (other.experationDate != null)
				return false;
		} else if (!experationDate.equals(other.experationDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("CurrentAccountDeposit [experationDate=");
		builder.append(experationDate);
		builder.append(", creditLimit=");
		builder.append(creditLimit);
		builder.append("]");
		return builder.toString();
	}

}
