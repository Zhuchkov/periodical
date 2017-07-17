package periodical.controller.dto;

import periodical.model.entity.User;

public class EntryCreationInput {

	private User requestCreator;
	private String entryName;
	private String entryText;
	private int periodicalId;
	
	private EntryCreationInput(){
		
	}
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	public String getEntryText() {
		return entryText;
	}
	public void setEntryText(String entryText) {
		this.entryText = entryText;
	}

	public int getPeriodicalId() {
		return periodicalId;
	}
	public void setPeriodicalId(int periodicalId) {
		this.periodicalId = periodicalId;
	}

	public User getRequestCreator() {
		return requestCreator;
	}
	public void setRequestCreator(User requestCreator) {
		this.requestCreator = requestCreator;
	}



	public static class Builder{
		private EntryCreationInput instance = new EntryCreationInput();
		
		public Builder setEntryName(String entryName ){
			instance.entryName=entryName;
			return this;
		}
		public Builder setEntryText(String entryText) {
			instance.entryText = entryText;
			return this;
		}
		public Builder setPeriodicalId(int publisherId) {
			instance.periodicalId = publisherId;
			return this;
		}
		public Builder setRequestCreator(User requestCreator) {
			instance.setRequestCreator(requestCreator);
			return this;
		}
		public EntryCreationInput build(){
			return instance;
		}
	}
	
}
