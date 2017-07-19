package periodical.model.dao;

import java.util.List;
import java.util.Optional;

import periodical.controller.dto.PeriodicalSearchParameters;
import periodical.controller.dto.UserDetailsPagination;
import periodical.model.entity.Periodical;
import periodical.model.entity.User;

public interface PeriodicalDao extends GenericDao<Periodical> {
	Optional<Periodical> findByName();

	List<Periodical> findPeriodicalsByPublisherId(int userId, UserDetailsPagination paginationParams);

	List<Periodical> findWithParameters(PeriodicalSearchParameters searchParameters);

	Optional<Periodical> findWithUser(User user, int periodicalId);


}
