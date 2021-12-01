package by.zastr.xml.entity;

import java.time.LocalDate;


public class SavingDeposit extends Deposit{
	private LocalDate experationDate;
	public SavingDeposit(String bankName, String country, Depositor depositor, String accountIdString,
			long amountOnDeposit) {
		super(bankName, country, depositor, accountIdString, amountOnDeposit);
	}

	public LocalDate getDate() {
		return experationDate;
	}

	public void setDate(LocalDate date) {
		this.experationDate = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		SavingDeposit other = (SavingDeposit) obj;
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
		builder.append("SavingDeposit [date=");
		builder.append(experationDate);
		builder.append("]");
		return builder.toString();
	}
}
