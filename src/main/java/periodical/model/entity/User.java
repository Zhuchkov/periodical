package periodical.model.entity;

public class User {
	private int id;
	private String email;
	private String password;
	private Role role;
	private UserDetails userDetails;

	User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public static class Builder {
		private User instance = new User();

		public Builder setId(int id) {
			instance.id = id;
			return this;
		}

		public Builder setEmail(String email) {
			instance.email = email;
			return this;
		}

		public Builder setPassword(String password) {
			instance.password = password;
			return this;
		}

		public Builder setRole(Role role) {
			instance.role = role;
			return this;
		}

		public Builder setUserDetails(UserDetails userDetails){
			instance.userDetails = userDetails;
			return this;
		}

		public User build() {
			return instance;
		}
	}

}