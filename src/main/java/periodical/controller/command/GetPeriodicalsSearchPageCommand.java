package periodical.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.PeriodicalSearchParameters;
import periodical.controller.dto.SortParam;
import periodical.controller.validation.ValidationError;
import periodical.controller.validation.ValidatorFactory;
import periodical.controller.validation.validator.PeriodicalSearchValidator;
import periodical.model.entity.Category;
import periodical.model.entity.Periodical;
import periodical.model.service.CategoryService;
import periodical.model.service.PeriodicalService;

public class GetPeriodicalsSearchPageCommand implements Command {

	PeriodicalService periodicalService; 
	CategoryService categoryService;
	ValidatorFactory validatorFactory;
	public GetPeriodicalsSearchPageCommand(PeriodicalService periodicalService,CategoryService categoryService,ValidatorFactory validatorFactory) {
		this.periodicalService=periodicalService;
		this.categoryService=categoryService;
		this.validatorFactory=validatorFactory;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Category> categories = categoryService.getCategories();
		request.setAttribute("categories", categories);
		
		if (request.getParameterMap().containsKey("searchParam")) {
			PeriodicalSearchParameters searchParameters = extractSearchParameters(request);
			PeriodicalSearchValidator validator = validatorFactory.createPeriodicalSearchValidator();
			List<ValidationError> errors = validator.validate(searchParameters);
			
			if (errors.isEmpty()) {
				searchParameters.setCategory(categories.get(Integer.valueOf(request.getParameter("category"))));
				List<Periodical> foundPeriodicals = periodicalService.findPeriodicalsWithParameters(searchParameters);
				request.setAttribute("foundPeriodicals", foundPeriodicals);
			}else{
				request.setAttribute("errors", errors);
			}
		}
		return "/WEB-INF/jsp/periodicalSearch.jsp";
	}

	private PeriodicalSearchParameters extractSearchParameters(HttpServletRequest request) {
//		System.out.println(request.getParameter("periodicalName"));
//		System.out.println(request.getParameter("publisherName"));
//		System.out.println(request.getParameter("maxPrice"));
//		System.out.println(request.getParameter("sortParam"));
		return new PeriodicalSearchParameters.Builder()
				.setPeriodicalName(request.getParameter("periodicalName"))
				.setPublisherName(request.getParameter("publisherName"))
				.setMaxCost(request.getParameter("maxPrice"))
				.setSortParam(SortParam.valueOf(request.getParameter("sortParam")))
				.build();
	}

}
