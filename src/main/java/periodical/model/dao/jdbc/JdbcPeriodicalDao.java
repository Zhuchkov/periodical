package periodical.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import periodical.controller.dto.PeriodicalsSearchParameters;
import periodical.model.dao.PeriodicalDao;
import periodical.model.entity.Periodical;
import periodical.model.entity.User;
import periodical.model.entity.UserDetails;

public class JdbcPeriodicalDao implements PeriodicalDao {

	private static final String SELECT_FROM_PERIODICAL_BY_ID = "SELECT * FROM periodical WHERE id=?";
	private static final String SELECT_FROM_PERIODICAL_BY_ID_AND_USER = "SELECT * FROM periodical WHERE id=? AND publisher_id =?";
	private static final String SELECT_FROM_PERIODICAL_BY_PUBLISHER_ID = "SELECT * FROM periodical WHERE publisher_id = ?";
	private static final String INSERT_INTO_PERIODICAL = "INSERT INTO periodical(name,cost,publisher_id,category_id) VALUES(?,?,?,?)";
	private static final String ID_COL = "id";
	private static final String NAME_COL = "name";
	private static final String COST_COL = "cost";
	
	Connection connection;

	public JdbcPeriodicalDao(Connection connection){
		this.connection=connection;
	}

	@Override
	public Optional<Periodical> find(int id) {
		Optional<Periodical> result = Optional.empty();
		try(PreparedStatement query =
                connection.prepareStatement(SELECT_FROM_PERIODICAL_BY_ID)){
			query.setInt(1, id);
			ResultSet resultSet = query.executeQuery();
			if(resultSet.next()){
				result = Optional.of( extractPeriodicalFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
		return result;
	}
	@Override
	public Optional<Periodical> findWithUser(User user, int periodicalId) {
			Optional<Periodical> result = Optional.empty();
			try(PreparedStatement query =
	                connection.prepareStatement(SELECT_FROM_PERIODICAL_BY_ID_AND_USER)){
				query.setInt(1, periodicalId);
				query.setInt(2, user.getId());
				ResultSet resultSet = query.executeQuery();
				if(resultSet.next()){
					result = Optional.of( extractPeriodicalFromResultSet(resultSet));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				 throw new RuntimeException(e);
			}
			return result;
	}


	@Override
	public List<Periodical> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Periodical insert(Periodical periodical) {
		try(PreparedStatement query =
                connection.prepareStatement(INSERT_INTO_PERIODICAL,Statement.RETURN_GENERATED_KEYS)){
			query.setString(1, periodical.getName());
			query.setBigDecimal(2, periodical.getCost());
			query.setInt(3, periodical.getPublisher().getId());
			query.setInt(4, periodical.getCategory().getId());
			query.executeUpdate();
			ResultSet keys =  query.getGeneratedKeys();
			if(keys.next()){
				periodical.setId(keys.getInt(1));
			}
			return periodical;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Periodical update(Periodical e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Periodical delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Periodical> findByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Periodical> findPeriodicalsByPublisherId(int publisherId) {
		List<Periodical> result = new LinkedList<>();
		try(PreparedStatement query =
                connection.prepareStatement(SELECT_FROM_PERIODICAL_BY_PUBLISHER_ID)){
			query.setInt(1, publisherId);
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()){
				result.add( extractPeriodicalFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public List<Periodical> findWithParameters(PeriodicalsSearchParameters searchParameters) {
		String Querry = buildQuerry(searchParameters);
		List<Periodical> result = new LinkedList<>();
		try(PreparedStatement query =
                connection.prepareStatement(Querry)){
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()){
				Periodical current = extractPeriodicalFromResultSet(resultSet);
				current.setPublisher(extractPublisherFromResultSet(resultSet));
				result.add(current);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
	}
	private String buildQuerry(PeriodicalsSearchParameters searchParameters) {
		String baseQuerry =  "SELECT periodical.id,periodical.name, periodical.cost, user_detail.first_name, user_detail.last_name, user_detail.id FROM periodical  JOIN category ON category_id = category.id JOIN user_detail on publisher_id=user_detail.id";
		StringBuilder sb = new StringBuilder(baseQuerry);
		if(searchParameters.hasWhereConstraints()){
			
			sb.append(" Where");
			boolean firstParam = true;
			if(searchParameters.hasCategory()){
				sb.append(" category.id = "+searchParameters.getCategory().getId());
				firstParam=false;
			}
			if(searchParameters.hasPeriodicalName()){
				if(!firstParam){
					sb.append(" AND ");
				}
				sb.append(" periodical.name LIKE '"+searchParameters.getPeriodicalName()+"%'");
			}
			if(searchParameters.hasPublisherName()){
				if(!firstParam){
					sb.append(" AND ");
				}
				sb.append(" user_detail.first_name LIKE '"+searchParameters.getPublisherName()+"%'");
			}
			if(searchParameters.hasMaxCost()){
				if(!firstParam){
					sb.append(" AND ");
				}
				sb.append(" periodical.cost >"+searchParameters.getMaxCost());
			}
			
		}
		sb.append(" ORDER BY "+searchParameters.getSortParam().getColumnName());
		if(searchParameters.isDescending()){
			sb.append(" desc");
		}
		return sb.toString();
	}

	private UserDetails extractPublisherFromResultSet(ResultSet resultSet) throws SQLException {
		return new UserDetails.Builder()
				.setId(resultSet.getInt("user_detail.id"))
				.setFirstName((resultSet.getString("user_detail.first_name")))
				.setLastName((resultSet.getString("user_detail.last_name")))
				.build();
	}

	private Periodical extractPeriodicalFromResultSet(ResultSet resultSet) throws SQLException {
		return new Periodical.Builder()
				.setId(resultSet.getInt(ID_COL))
				.setName(resultSet.getString(NAME_COL))
				.setCost(resultSet.getBigDecimal(COST_COL))
				.build();		
	}

	
}
