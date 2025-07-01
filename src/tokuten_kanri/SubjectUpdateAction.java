package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String schoolCd = null;

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher != null) {
            schoolCd = teacher.getSchool();  // ここが getSchool() で取得
        }

        if (schoolCd == null || schoolCd.isEmpty()) {
            request.setAttribute("message", "学校コードが見つかりません（ログイン情報が無効です）。");
            return "subject_update.jsp";
        }

        String cd = request.getParameter("cd");
        if (cd == null || cd.isEmpty()) {
            request.setAttribute("message", "科目コードが指定されていません。");
            return "subject_update.jsp";
        }

        SubjectDAO dao = new SubjectDAO();
        Subject subject = dao.findByPrimaryKey(schoolCd, cd);

        if (subject == null) {
            request.setAttribute("message", "指定された科目が見つかりません。");
        } else {
            request.setAttribute("subject", subject);
        }

        return "subject_update.jsp";
    }
}
