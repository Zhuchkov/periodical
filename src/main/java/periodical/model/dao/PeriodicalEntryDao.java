package periodical.model.dao;

import java.util.List;

import periodical.model.entity.PeriodicalEntry;

public interface PeriodicalEntryDao extends GenericDao<PeriodicalEntry>{

	List<PeriodicalEntry> findAllBySubscriptionAndSubscriber(int requestCreatorId, int subscriptionId);

}
