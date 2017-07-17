package periodical.model.dao;

import java.util.Optional;

import periodical.model.entity.User;

public interface UserDao extends GenericDao<User>{
	Optional<User> findByEmail(String email);

}
