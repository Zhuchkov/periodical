package periodical.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import periodical.model.dao.CategoryDao;
import periodical.model.entity.Category;

public class JdbcCategoryDao implements CategoryDao {
	
	public static final String FIND_ALL = "SELECT * FROM category";
	public static final String SELECT_FROM_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?";
	public static final String INSERT_CATEGORY = "INSERT INTO category(name) VALUES(?)";
	
	private static final String ID_COL = "id";
	private static final String NAME_COL = "name"; 
	
	Connection connection;

	public JdbcCategoryDao(Connection connection) {
		this.connection=connection;
	}

	@Override
	public Optional<Category> find(int id) {
		Optional<Category> result = Optional.empty();
		try(PreparedStatement query =
                connection.prepareStatement(SELECT_FROM_CATEGORY_BY_ID)){
			query.setInt(1, id);
			ResultSet resultSet = query.executeQuery();
			if(resultSet.next()){
				result = Optional.of(extractCategoryFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
		return result;
	}

	
	@Override
	public List<Category> findAll() {
	List<Category> result = new LinkedList<>();
		try(PreparedStatement query =
                connection.prepareStatement(FIND_ALL)){
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()){
				result.add(extractCategoryFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public Category insert(Category category) {
		try(PreparedStatement query =
                connection.prepareStatement(INSERT_CATEGORY,
                		Statement.RETURN_GENERATED_KEYS)){
			query.setString(1,category.getName());
			query.executeUpdate();
			ResultSet keys =  query.getGeneratedKeys();
			if(keys.next()){
				category.setId(keys.getInt(1));
			}
			return category;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Category update(Category e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Category extractCategoryFromResultSet(ResultSet resultSet) throws SQLException {
		return new Category.Builder()
				.setId(resultSet.getInt(ID_COL))
				.setName(resultSet.getString(NAME_COL))
				.build();
	}

}
