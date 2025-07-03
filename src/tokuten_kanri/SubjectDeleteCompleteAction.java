package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteCompleteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd");

        SubjectDAO dao = new SubjectDAO();
        dao.delete(cd);

        return "subject_delete_done.jsp";
    }
}
