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
				int categoryOrigin = Integer.valueOf(request.getParameter("category"));
				searchParameters.setCategory(categories.get(categoryOrigin));
				setPageOffset(request, searchParameters);
				List<Periodical> foundPeriodicals = periodicalService.findPeriodicalsWithParameters(searchParameters);
				request.setAttribute("categoryOrigrn", categoryOrigin);
				request.setAttribute("foundPeriodicals", foundPeriodicals);
				request.setAttribute("searchParameters", searchParameters);
			}else{
				request.setAttribute("errors", errors);
			}
		}
		return Page.PERIODICAL_SEARCH_JSP;
	}

	private void setPageOffset(HttpServletRequest request, PeriodicalSearchParameters searchParameters) {
		String pageNumberParam = request.getParameter("pageNumber");
		if (pageNumberParam != null) {
			int pageNumber = Integer.valueOf(pageNumberParam);
			searchParameters.setPageNumber(pageNumber);
		}
	}

	private PeriodicalSearchParameters extractSearchParameters(HttpServletRequest request) {
		PeriodicalSearchParameters params = new PeriodicalSearchParameters.Builder()
				.setPeriodicalName(request.getParameter("periodicalName"))
				.setPublisherName(request.getParameter("publisherName"))
				.setMaxCost(request.getParameter("maxPrice"))
				.setSortParam(SortParam.valueOf(request.getParameter("sortParam")))
				.build();
		if(request.getParameter("order")!= null&&request.getParameter("order").equals("descending")){
			params.setDescending(true);
		}
		return params;
	}

}
