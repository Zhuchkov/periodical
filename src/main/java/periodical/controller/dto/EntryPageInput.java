package periodical.controller.dto;

public class EntryPageInput {
	public static final int DEFAULT_START_PAGE = 0;
	public static final int DEFAULT_PAGE_LENGTH = 1;

	private int userId;
	private int subscriptionId;

	private int entryPage = DEFAULT_START_PAGE;
	private int entryPageLength = DEFAULT_PAGE_LENGTH;

	private EntryPageInput() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getEntryPage() {
		return entryPage;
	}

	public void setEntryPage(int entryPage) {
		this.entryPage = entryPage;
	}

	public int getEntryPageLength() {
		return entryPageLength;
	}

	public void setEntryPageLength(int entryPageLength) {
		this.entryPageLength = entryPageLength;
	}

	public static class Builder {
		private EntryPageInput instance = new EntryPageInput();

		public Builder setUserId(int userId) {
			instance.userId = userId;
			return this;
		}

		public Builder setSubscriptionId(int subscriptionId) {
			instance.subscriptionId = subscriptionId;
			return this;
		}

		public Builder setEntryPage(int entryPage) {
			instance.entryPage = entryPage;
			return this;
		}

		public Builder setEntryPageLength(int entryPageLength) {
			instance.entryPageLength = entryPageLength;
			return this;
		}

		public EntryPageInput build() {
			return instance;
		}
	}
}
