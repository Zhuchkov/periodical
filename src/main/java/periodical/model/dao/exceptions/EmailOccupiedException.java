package periodical.model.dao.exceptions;

import periodical.model.entity.User;

public class EmailOccupiedException extends RuntimeException {

	private User user;
	public EmailOccupiedException(Throwable cause, User user) {
        super(cause);
        this.user=user;
    }
	public User getUser(){
		return user;
	}
}
