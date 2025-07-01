package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateAction extends Action {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setCharacterEncoding("UTF-8");

    HttpSession session = request.getSession();
    Teacher teacher = (Teacher) session.getAttribute("teacher");
    if (teacher == null) {
      // ログインしていなければログイン画面へ戻す
      return "login.jsp";
    }

    String schoolCd = teacher.getSchool();
    StudentDAO dao = new StudentDAO();

    String noStr = request.getParameter("no");
    if (noStr == null || noStr.trim().isEmpty()) {
      // 学生番号指定がないなら一覧へ戻す（もしくはエラー表示）
      return "StudentList.action";
    }

    int no = 0;
    try {
      no = Integer.parseInt(noStr);
    } catch (NumberFormatException e) {
      // 不正なnoの場合も一覧へ
      return "StudentList.action";
    }

    // 学生情報を取得（絞り込みにschoolCdも使うのは念のため）
    Student student = null;
    for (Student s : dao.filter(null, null, null, schoolCd)) {
      if (s.getNo() == no) {
        student = s;
        break;
      }
    }

    if (student == null) {
      // 該当学生なし
      request.setAttribute("message", "該当する学生情報が見つかりません。");
      return "StudentList.action";
    }

    // 学生情報をリクエスト属性へセット
    request.setAttribute("student", student);

    return "student_update.jsp";
  }
}
