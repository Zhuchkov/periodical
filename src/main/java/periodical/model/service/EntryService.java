package periodical.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import periodical.controller.dto.EntryCreationInput;
import periodical.model.dao.DaoFactory;
import periodical.model.dao.PeriodicalEntryDao;
import periodical.model.dao.SubscriptionDao;
import periodical.model.entity.Periodical;
import periodical.model.entity.PeriodicalEntry;
import periodical.model.entity.User;

public class EntryService {
	private static class Holder{
		private static final EntryService INSTANCE = new EntryService(DaoFactory.getInstance(),
																		PeriodicalService.getInstance());
	}
	
	public static EntryService getInstance(){
		return Holder.INSTANCE;
	}
	
	private DaoFactory factory;
	private PeriodicalService periodicalService;
	
	EntryService(DaoFactory factory, PeriodicalService periodicalService) {
		this.factory=factory;
		this.periodicalService=periodicalService;
	}
	
	public Optional<PeriodicalEntry> saveEntryAndNotifySubscribers(EntryCreationInput entryParams) {
		try (Connection connection = factory.getConnection()) {
			Optional<PeriodicalEntry> result = Optional.empty();
			Optional<Periodical> periodical = periodicalService.findPeriodicalByUserAndId(entryParams.getRequestCreator(),
																		entryParams.getPeriodicalId());
			if (periodical.isPresent()) {
				PeriodicalEntry entry = extractEntryFromCreationParameters(entryParams);
				entry.setPeriodical(periodical.get());
				
				PeriodicalEntryDao entryDao = factory.createPeriodicalEntryDao(connection);
				entry = entryDao.insert(entry);
				
				SubscriptionDao subscriptionDao = factory.createSubscriptionDao(connection);
				subscriptionDao.notifySubscribers(periodical.get());
				
				result = Optional.of(entry);
				
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}
	public List<PeriodicalEntry> getLastAvailableEntries(User requestCreator, int subscriptionId) {
		try(Connection connection = factory.getConnection()){
			PeriodicalEntryDao entryDao = factory.createPeriodicalEntryDao(connection);
			List<PeriodicalEntry> entries =entryDao.findAllBySubscriptionAndSubscriber(requestCreator.getId(),subscriptionId);
			return entries;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	private PeriodicalEntry extractEntryFromCreationParameters(EntryCreationInput entryParams) {
		return new PeriodicalEntry.Builder().setName(entryParams.getEntryName()).setData(entryParams.getEntryText())
				.setCreationTime(LocalDateTime.now()).build();
	}

	
}
