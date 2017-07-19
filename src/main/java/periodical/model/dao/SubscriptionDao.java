package periodical.model.dao;

import java.util.List;
import java.util.Optional;

import periodical.controller.dto.UserDetailsPagination;
import periodical.model.entity.Periodical;
import periodical.model.entity.Subscription;
import periodical.model.entity.User;

public interface SubscriptionDao extends GenericDao<Subscription> {
	Optional<Subscription> findBySubscriberAndPeriodical(User user, Periodical periodical);
	List<Subscription> findSubscriptionsBySubscriberId(int subscriberId, UserDetailsPagination paginationParams);
	List<Subscription> findSubscriptionsByPeriodicalId(int periodicalId);
	void notifySubscribers(Periodical periodical);
}
