package periodical.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import periodical.controller.dto.EntryPageInput;
import periodical.model.dao.PeriodicalEntryDao;
import periodical.model.entity.PeriodicalEntry;

public class JdbcPeriodicalEntryDao implements PeriodicalEntryDao{

	private static final String INSERT_ENTRY = "INSERT INTO periodical_entry(name, data, creation_time, periodical_id) VALUES(?,?,?,?)";

	private static final String SELECT_ALL_AVAILABLE_ENTRY = "SELECT * FROM periodical_entry entry JOIN subscription ON entry.periodical_id = subscription.periodical_id WHERE subscription.id = ? AND subscription.subscriber_id = ? AND subscription.last_available_entry_date > entry.creation_time ORDER BY creation_time DESC LIMIT ?,?";

	private static final String ID = "entry.id";

	private static final String NAME = "entry.name";

	private static final String DATA = "entry.data";

	private static final String CREATION_TIME = "entry.creation_time";
	
	Connection connection;
	public JdbcPeriodicalEntryDao(Connection connection) {
		this.connection=connection;
	}
	@Override
	public PeriodicalEntry insert(PeriodicalEntry entry) {
		try(PreparedStatement query =
                connection.prepareStatement(INSERT_ENTRY,
                		Statement.RETURN_GENERATED_KEYS)){
			query.setString(1,entry.getName());
			query.setString(2, entry.getData());
			query.setLong(3, entry.getCreationTime().toEpochSecond(ZoneOffset.UTC));
			query.setInt(4, entry.getPeriodical().getId());
			query.executeUpdate();
			ResultSet keys =  query.getGeneratedKeys();
			if(keys.next()){
				entry.setId(keys.getInt(1));
			}
			return entry;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<PeriodicalEntry> findAllBySubscriptionAndSubscriber(EntryPageInput inputParams) {
		try(PreparedStatement query =
                connection.prepareStatement(SELECT_ALL_AVAILABLE_ENTRY)){
			List<PeriodicalEntry> result = new LinkedList<>();
			query.setInt(1,inputParams.getSubscriptionId());
			query.setInt(2, inputParams.getUserId());
			query.setInt(3, inputParams.getEntryPage()*inputParams.getEntryPageLength());
			query.setInt(4, inputParams.getEntryPageLength());
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()){
				result.add(extractEntry(resultSet));
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private PeriodicalEntry extractEntry(ResultSet resultSet) throws SQLException {
		return new PeriodicalEntry.Builder()
				.setId(resultSet.getInt(ID))
				.setName(resultSet.getString(NAME))
				.setData(resultSet.getString(DATA))
				.setCreationTime(LocalDateTime.ofEpochSecond(resultSet.getLong(CREATION_TIME), 0, ZoneOffset.UTC))
				.build();
	}
	@Override
	public Optional<PeriodicalEntry> find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PeriodicalEntry> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public PeriodicalEntry update(PeriodicalEntry e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PeriodicalEntry delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
