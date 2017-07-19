package periodical.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import periodical.controller.dto.EntryCreationInput;
import periodical.controller.dto.PeriodicalSearchParameters;
import periodical.model.dao.CategoryDao;
import periodical.model.dao.DaoFactory;
import periodical.model.dao.PeriodicalDao;
import periodical.model.dao.PeriodicalEntryDao;
import periodical.model.dao.SubscriptionDao;
import periodical.model.entity.Category;
import periodical.model.entity.Periodical;
import periodical.model.entity.PeriodicalEntry;
import periodical.model.entity.User;

public class PeriodicalService {
	private DaoFactory factory;

	private static class Holder {
		private static final PeriodicalService INSTANCE = new PeriodicalService(DaoFactory.getInstance());
	}

	public static PeriodicalService getInstance() {
		return Holder.INSTANCE;
	}

	PeriodicalService(DaoFactory factory) {
		this.factory = factory;
	}

	public Periodical create(Periodical periodical, int categoryId) {
		try (Connection connection = factory.getConnection()) {
			CategoryDao categoryDao = factory.createCategoryDao(connection);
			Optional<Category> category = categoryDao.find(categoryId);
			PeriodicalDao periodicalDao = factory.createPeriodicalDao(connection);
			periodical.setCategory(category.get());
			return periodicalDao.insert(periodical);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

//	public List<Periodical> findAllPeriodicalsByPublisher(User user) {
//		try (Connection connection = factory.getConnection()) {
//			PeriodicalDao periodicalDao = factory.createPeriodicalDao(connection);
//			return periodicalDao.findPeriodicalsByPublisherId(user.getId());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException(e);
//		}
//
//	}

	public List<Periodical> findPeriodicalsWithParameters(PeriodicalSearchParameters searchParameters) {
		try (Connection connection = factory.getConnection()) {
			PeriodicalDao periodicalDao = factory.createPeriodicalDao(connection);
			return periodicalDao.findWithParameters(searchParameters);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public Optional<Periodical> findPeriodicalByUserAndId(User user, int periodicalId) {
		try (Connection connection = factory.getConnection()) {
			PeriodicalDao periodicalDao = factory.createPeriodicalDao(connection);
			return periodicalDao.findWithUser(user, periodicalId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}



	

}
