package periodical.controller.dto;

public class LoginInput {
	private String email;
	private String password;
	
	private LoginInput(){
		
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

	public static class Builder{
		private LoginInput instance = new LoginInput();
		public Builder setEmail(String email) {
			instance.email=email;
			return this;
		}
		public Builder setPassword(String password) {
			instance.password=password;
			return this;
		}
		public LoginInput build(){
			return instance;
		}
	}

}
