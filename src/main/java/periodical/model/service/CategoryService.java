package periodical.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import periodical.model.dao.CategoryDao;
import periodical.model.dao.DaoFactory;
import periodical.model.entity.Category;

public class CategoryService {
	
	private static class Holder{
		private static final CategoryService INSTANCE = new CategoryService(DaoFactory.getInstance());
	}
	public static CategoryService getInstance(){
		return Holder.INSTANCE;
	}
	
	private DaoFactory factory;

	CategoryService(DaoFactory factory) {
		this.factory = factory;
	}
	
	public List<Category> getCategories(){
		try(Connection connection = factory.getConnection()){
			CategoryDao categoryDao = factory.createCategoryDao(connection);
			
			return categoryDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
}
