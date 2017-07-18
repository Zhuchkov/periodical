package periodical.view.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import periodical.model.entity.User;

public class InfoUserTag extends TagSupport {
	@Override
	public int doStartTag() throws JspException {
		HttpSession session =  pageContext.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null){
			JspWriter out = pageContext.getOut();
			try {
				out.write(user.getEmail());
			} catch (IOException e) {
				throw new JspException(e.getMessage());
			}
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return EVAL_PAGE;
	}
	
	

}
