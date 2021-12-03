package by.zastr.xml.entity;

public abstract class AbstractDeposit {
	public static final String NO_WEBSITE = "no website";
	private String accountId;
	private String bankName;
	private String country;
	private Depositor depositor;
	private long amountOnDeposit;
	private byte profitobility;
	private boolean revocable;
	private String website;
	
	public AbstractDeposit(String accountId, String bankName, String country, Depositor depositor, long amountOnDeposit,
			byte profitobility, boolean revocable) {
		super();
		this.accountId = accountId;
		this.bankName = bankName;
		this.country = country;
		this.depositor = depositor;
		this.amountOnDeposit = amountOnDeposit;
		this.profitobility = profitobility;
		this.revocable = revocable;
		this.website =NO_WEBSITE;
	}
	public AbstractDeposit() {
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public boolean isRevocable() {
		return revocable;
	}

	public void setRevocable(boolean revocable) {
		this.revocable = revocable;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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
	
	public long getAmountOnDeposit() {
		return amountOnDeposit;
	}
	
	public void setAmountOnDeposit(long amountOnDeposit) {
		this.amountOnDeposit = amountOnDeposit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + (int) (amountOnDeposit ^ (amountOnDeposit >>> 32));
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((depositor == null) ? 0 : depositor.hashCode());
		result = prime * result + profitobility;
		result = prime * result + (revocable ? 1231 : 1237);
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		AbstractDeposit other = (AbstractDeposit) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
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
		if (profitobility != other.profitobility)
			return false;
		if (revocable != other.revocable)
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Deposit [accountId=");
		builder.append(accountId);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", country=");
		builder.append(country);
		builder.append(", depositor=");
		builder.append(depositor);
		builder.append(", amountOnDeposit=");
		builder.append(amountOnDeposit);
		builder.append(", profitobility=");
		builder.append(profitobility);
		builder.append(", revocable=");
		builder.append(revocable);
		builder.append(", website=");
		builder.append(website);
		builder.append("]");
		return builder.toString();
	}
}
