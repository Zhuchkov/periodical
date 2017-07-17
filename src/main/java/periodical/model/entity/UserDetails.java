package periodical.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDetails {
	
	private int id;
	private String firstName;
	private String lastName;	
	private BigDecimal money;
	private List<Periodical> periodicals = new ArrayList<>();
	private List<Subscription> subscriptions = new ArrayList<>();
	

	UserDetails(){
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public List<Periodical> getPeriodicals() {
		return periodicals;
	}

	public void setPeriodicals(List<Periodical> periodicals) {
		this.periodicals = periodicals;
	}


	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}


	public static class Builder {
		
		private UserDetails instance = new UserDetails();
		
		public Builder setId(int id) {
			instance.id = id;
			return this;
		}

		public Builder setFirstName(String FirstName) {
			instance.firstName = FirstName;
			return this;
		}

		public Builder setLastName(String lastName) {
			instance.lastName = lastName;
			return this;
		}

		public Builder setMoney(BigDecimal money) {
			instance.money = money;
			return this;
		}
		public UserDetails build(){
			return instance;
		}
	}
	
}