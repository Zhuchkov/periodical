package periodical.controller.dto;

import java.math.BigDecimal;

public class PeriodicalCreationInput {

	private String name;
	private String cost;
	
	private PeriodicalCreationInput(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	public static class Builder{
		private PeriodicalCreationInput instance = new PeriodicalCreationInput();
		
		public Builder setName(String name) {
			instance.name = name;
			return this;
		}
	
		public Builder setCost(String cost) {
			instance.cost = cost;
			return this;
		}
		public PeriodicalCreationInput build(){
			return instance;
		}
	}
}
