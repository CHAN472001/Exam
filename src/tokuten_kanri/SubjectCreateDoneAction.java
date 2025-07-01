package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectCreateDoneAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            return "login.jsp";
        }

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");
        String schoolCd = teacher.getSchool();

        boolean hasError = false;

        if (cd == null || cd.trim().isEmpty() || cd.length() != 3) {
            request.setAttribute("errorCd", "科目コードは3文字で入力してください。");
            hasError = true;
        }

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("errorName", "科目名を入力してください。");
            hasError = true;
        }

        SubjectDAO dao = new SubjectDAO();

        if (!hasError && dao.exists(cd, schoolCd)) {
            request.setAttribute("errorCd", "この科目コードは既に存在します。");
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            return "subject_create.jsp";
        }

        Subject s = new Subject();
        s.setCd(cd);
        s.setName(name);
        s.setSchoolCd(schoolCd);

        dao.insert(s);

        return "subject_create_done.jsp";
    }
}
