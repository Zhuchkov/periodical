package periodical.controller.dto;

public class RegistrationInput {

	private String email;
	private String firstPassword;
	private String secondPassword;
	
	public String getFirstPassword() {
		return firstPassword;
	}
	public void setFirstPassword(String firstPassword) {
		this.firstPassword = firstPassword;
	}
	public String getSecondPassword() {
		return secondPassword;
	}
	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static class Builder{
		private RegistrationInput instance = new RegistrationInput();
		public Builder setEmail(String email) {
			instance.email=email;
					return this;
		}
		public Builder setFirstPassword(String firstPassword) {
			instance.firstPassword=firstPassword;
			return this;
		}
		public Builder setSecondPassword(String secondPassword) {
			instance.secondPassword=secondPassword;
			return this;
		}
		public RegistrationInput build(){
			return instance;
		}
	}
}
