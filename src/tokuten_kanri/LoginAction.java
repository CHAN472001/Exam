package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginAction extends Action {
    public String execute(
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        boolean hasError = false;

        // IDのチェック
        if (id == null || id.trim().isEmpty()) {
            request.setAttribute("errorId", "このフィールドを入力してください");
            hasError = true;
        } else {
            request.setAttribute("id", id);  // 入力済みIDをフォームに保持
        }

        // パスワードのチェック
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("errorPassword", "このフィールドを入力してください");
            hasError = true;
        }

        // 入力エラーがある場合、入力値を保持したままlogin-invalid.jspに戻る
        if (hasError) {
            return "login-invalid.jsp";
        }

        // ログイン認証
        TeacherDAO dao = new TeacherDAO();
        Teacher teacher = dao.search(id, password);

        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            return "menu.jsp";
        }

        // 認証失敗
        return "login-error.jsp";
    }
}
