package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateDoneAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String schoolCd = request.getParameter("school_cd");
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        if (schoolCd == null || cd == null || name == null || name.trim().isEmpty()) {
            request.setAttribute("message", "入力に不備があります。");
            return "subject_update.jsp";
        }

        Subject subject = new Subject();
        subject.setSchoolCd(schoolCd);
        subject.setCd(cd);
        subject.setName(name.trim());

        SubjectDAO dao = new SubjectDAO();
        boolean updated = dao.update(subject);

        if (!updated) {
            request.setAttribute("message", "更新に失敗しました。");
            return "subject_update.jsp";
        }

        return "subject_update_done.jsp";
    }
}
