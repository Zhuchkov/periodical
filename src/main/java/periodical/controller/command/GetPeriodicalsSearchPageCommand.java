package periodical.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.PeriodicalsSearchParameters;
import periodical.controller.dto.SortParam;
import periodical.model.entity.Category;
import periodical.model.entity.Periodical;
import periodical.model.service.CategoryService;
import periodical.model.service.PeriodicalService;

public class GetPeriodicalsSearchPageCommand implements Command {

	PeriodicalService periodicalService; 
	CategoryService categoryService;
	public GetPeriodicalsSearchPageCommand(PeriodicalService periodicalService,CategoryService categoryService) {
		this.periodicalService=periodicalService;
		this.categoryService=categoryService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Category> categories = categoryService.getCategories();
		request.setAttribute("categories", categories);
		
		if(request.getParameterMap().containsKey("searchParam")){
			PeriodicalsSearchParameters searchParameters = extractSearchParameters(request);
			searchParameters.setCategory(categories.get(Integer.valueOf(request.getParameter("category"))));
			List<Periodical> foundPeriodicals = periodicalService.findPeriodicalsWithParameters(searchParameters);
			request.setAttribute("foundPeriodicals", foundPeriodicals);
		}
		return "/WEB-INF/jsp/periodicalSearch.jsp";
	}

	private PeriodicalsSearchParameters extractSearchParameters(HttpServletRequest request) {
		return new PeriodicalsSearchParameters.Builder()
				.setPeriodicalName(request.getParameter("periodicalName"))
				.setPublisherName(request.getParameter("publisherName"))
				.setMaxCost(Long.valueOf(request.getParameter("maxPrice")))
				.setSortParam(SortParam.valueOf(request.getParameter("sortParam")))
				.build();
	}

}
