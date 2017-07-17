package periodical.model.dao;

import java.util.List;
import java.util.Optional;

import periodical.controller.dto.PeriodicalsSearchParameters;
import periodical.model.entity.Periodical;
import periodical.model.entity.User;

public interface PeriodicalDao extends GenericDao<Periodical> {
	Optional<Periodical> findByName();

	List<Periodical> findPeriodicalsByPublisherId(int userId);

	List<Periodical> findWithParameters(PeriodicalsSearchParameters searchParameters);

	Optional<Periodical> findWithUser(User user, int periodicalId);


}
