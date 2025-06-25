package tokuten_kanri;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentAction extends Action {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // セッションを取得（必要なら）
    HttpSession session = request.getSession();

    // 例：セッションに何か保存したい場合
    // session.setAttribute("lastAccessTime", System.currentTimeMillis());

    // DAOを使ってすべての学生データを取得
    StudentDAO dao = new StudentDAO();
    List<Student> students = dao.findAll();

    // JSPに渡すため、リクエストスコープに保存
    request.setAttribute("students", students);

    // 表示用JSPにフォワード
    return "student.jsp";
  }
}
