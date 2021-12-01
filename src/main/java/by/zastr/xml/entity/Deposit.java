package by.zastr.xml.entity;

public abstract class Deposit {
	private int id;
	private String bankName;
	private String country;
	private Depositor depositor;
	private String accountIdString;
	private long amountOnDeposit;
	private byte profitobility;
	
	public Deposit(int id, String bankName, String country, Depositor depositor, String accountIdString,
			long amountOnDeposit, byte profitobility) {
		super();
		this.id = id;
		this.bankName = bankName;
		this.country = country;
		this.depositor = depositor;
		this.accountIdString = accountIdString;
		this.amountOnDeposit = amountOnDeposit;
		this.profitobility = profitobility;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getProfitobility() {
		return profitobility;
	}

	public void setProfitobility(byte profitobility) {
		this.profitobility = profitobility;
	}

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Depositor getDepositor() {
		return depositor;
	}
	public void setDepositor(Depositor depositor) {
		this.depositor = depositor;
	}
	public String getAccountIdString() {
		return accountIdString;
	}
	public void setAccountIdString(String accountIdString) {
		this.accountIdString = accountIdString;
	}
	public long getAmountOnDeposit() {
		return amountOnDeposit;
	}
	public void setAmountOnDeposit(long amountOnDeposit) {
		this.amountOnDeposit = amountOnDeposit;
	}
	
	public byte getProfibility() {
		return profitobility;
	}
	public void setProfibility(byte profibility) {
		this.profitobility = profibility;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountIdString == null) ? 0 : accountIdString.hashCode());
		result = prime * result + (int) (amountOnDeposit ^ (amountOnDeposit >>> 32));
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((depositor == null) ? 0 : depositor.hashCode());
		result = prime * result + id;
		result = prime * result + profitobility;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deposit other = (Deposit) obj;
		if (accountIdString == null) {
			if (other.accountIdString != null)
				return false;
		} else if (!accountIdString.equals(other.accountIdString))
			return false;
		if (amountOnDeposit != other.amountOnDeposit)
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (depositor == null) {
			if (other.depositor != null)
				return false;
		} else if (!depositor.equals(other.depositor))
			return false;
		if (id != other.id)
			return false;
		if (profitobility != other.profitobility)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Deposit [id=");
		builder.append(id);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", country=");
		builder.append(country);
		builder.append(", depositor=");
		builder.append(depositor);
		builder.append(", accountIdString=");
		builder.append(accountIdString);
		builder.append(", amountOnDeposit=");
		builder.append(amountOnDeposit);
		builder.append(", profitobility=");
		builder.append(profitobility);
		builder.append("]");
		return builder.toString();
	}	
}
