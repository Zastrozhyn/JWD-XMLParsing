package by.zastr.xml.entity;

public class CurrentAccountDeposit extends AbstractDeposit {
	private long creditLimit;

	public CurrentAccountDeposit(String accountId, String bankName, String country, Depositor depositor,
			long amountOnDeposit, byte profitobility, boolean revocable) {
		super(accountId, bankName, country, depositor, amountOnDeposit, profitobility, revocable);
	}
	public CurrentAccountDeposit() {
		setDepositor(new Depositor());
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
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("CurrentAccountDeposit [creditLimit=");
		builder.append(creditLimit);
		builder.append("]");
		return builder.toString();
	}
}
