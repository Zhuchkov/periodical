package periodical.controller.dto;

public class UserDetailsInput {

	private String firstName;
	private String lastName;
	private UserDetailsInput(){
		
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
	public void setLastName(String secondName) {
		this.lastName = secondName;
	}
	public static class Builder{
		UserDetailsInput instance = new UserDetailsInput();
		public Builder setFirstName(String firstName) {
			instance.firstName=firstName;
			return this;
		}
		public Builder setLastName(String secondName) {
			instance.lastName=secondName;
			return this;
		}
		public UserDetailsInput build(){
			return instance;
		}
	}
	
}
