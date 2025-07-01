package tokuten_kanri;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            return "login.jsp";
        }

        String schoolCd = teacher.getSchool();
        SubjectDAO dao = new SubjectDAO();
        List<Subject> subjects = dao.findAll(schoolCd);

        request.setAttribute("subjects", subjects);
        return "subject_list.jsp";
    }
}
