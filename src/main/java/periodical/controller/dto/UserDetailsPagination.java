package periodical.controller.dto;

public class UserDetailsPagination {
	public static final int DEFAULT_PAGE_LENGTH =5;
	public static final int DEFAULT_START_PAGE =5;
	private int subscriptionPage = DEFAULT_START_PAGE ;
	private int subscriptionPageLength =DEFAULT_PAGE_LENGTH ;
	
	private int periodicalPage = DEFAULT_START_PAGE;
	private int periodicalPageLength = DEFAULT_PAGE_LENGTH;
	
	private UserDetailsPagination(){
		
	}

	public int getSubscriptionPage() {
		return subscriptionPage;
	}

	public void setSubscriptionPage(int subscriptionPage) {
		this.subscriptionPage = subscriptionPage;
	}

	public int getSubscriptionPageLength() {
		return subscriptionPageLength;
	}

	public void setSubscriptionPageLength(int subscriptionPageLength) {
		this.subscriptionPageLength = subscriptionPageLength;
	}

	public int getPeriodicalPage() {
		return periodicalPage;
	}

	public void setPeriodicalPage(int periodicalPage) {
		this.periodicalPage = periodicalPage;
	}

	public int getPeriodicalPageLength() {
		return periodicalPageLength;
	}

	public void setPeriodicalPageLength(int periodicalPageLength) {
		this.periodicalPageLength = periodicalPageLength;
	}
	
	public static class Builder{
		private UserDetailsPagination instance = new UserDetailsPagination();
		public Builder setSubscriptionPage(int subscriptionPage) {
			instance.subscriptionPage=subscriptionPage;
			return this;
		}
		public Builder setSubscriptionPageLength(int subscriptionPageLength) {
			instance.subscriptionPageLength=subscriptionPageLength;
			return this;
		}
		public Builder setPeriodicalPage(int periodicalPage) {
			instance.periodicalPage=periodicalPage;
			return this;
		}
		public Builder setPeriodicalPageLength(int periodicalPageLength) {
			instance.periodicalPageLength =periodicalPageLength;
			return this;
		}
		public UserDetailsPagination build(){
			return instance;
		}
	}

}
