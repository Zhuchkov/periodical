package periodical.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.Category;
import periodical.model.service.CategoryService;

public class GetPeriodicalCreationPageCommand implements Command {

	CategoryService categoryService;
	GetPeriodicalCreationPageCommand(CategoryService categoryService){
		this.categoryService=categoryService;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Category> categories = categoryService.getCategories();
		request.setAttribute("categories", categories);
		return "/WEB-INF/jsp/periodicalCreation.jsp";
	}
}
