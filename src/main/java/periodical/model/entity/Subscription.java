package periodical.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Subscription {

	private int id;
	private UserDetails subscriber;
	private Periodical periodical;
	private LocalDateTime lastAvailableEntryDate;
	private boolean updated;
	private boolean active;

	Subscription() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDetails getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(UserDetails subscriber) {
		this.subscriber = subscriber;
	}

	public Periodical getPeriodical() {
		return periodical;
	}

	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;
	}

	public LocalDateTime getLastAvailableEntryDate() {
		return lastAvailableEntryDate;
	}

	public void setLastAvailableEntryDate(LocalDateTime lastAvailableEntryDate) {
		this.lastAvailableEntryDate = lastAvailableEntryDate;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static class Builder {
		private Subscription instance = new Subscription();

		public Builder setId(int id) {
			instance.id = id;
			return this;
		}

		public Builder setSubscriber(UserDetails subscriber) {
			instance.subscriber = subscriber;
			return this;
		}

		public Builder setPeriodical(Periodical periodical) {
			instance.periodical = periodical;
			return this;
		}

		public Builder setLastAvailableEntryDate(LocalDateTime lastAvailableEntryDate) {
			instance.lastAvailableEntryDate = lastAvailableEntryDate;
			return this;
		}

		public Builder setUpdated(boolean updated) {
			instance.updated = updated;
			return this;
		}

		public Builder setActive(boolean active) {
			instance.active = active;
			return this;
		}

		public Subscription build() {
			return instance;
		}
	}
	

}
