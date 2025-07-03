package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd");

        SubjectDAO dao = new SubjectDAO();
        Subject subject = dao.get(cd);

        request.setAttribute("subject", subject);
        return "subject_delete.jsp";
    }
}
