package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateCompleteAction extends Action {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setCharacterEncoding("UTF-8");

    HttpSession session = request.getSession();
    Teacher teacher = (Teacher) session.getAttribute("teacher");
    if (teacher == null) {
      return "login.jsp";
    }

    StudentDAO dao = new StudentDAO();

    String schoolCd = teacher.getSchool();

    // パラメータ取得
    String noStr = request.getParameter("no");
    String name = request.getParameter("name");
    String classNumStr = request.getParameter("class_num");
    String isAttendStr = request.getParameter("is_attend");

    // 入学年度は変更不可なので画面に送信されていない想定（もしいれば無視）

    // バリデーション
    boolean hasError = false;

    if (noStr == null || noStr.trim().isEmpty()) {
      request.setAttribute("message", "学生番号が指定されていません。");
      hasError = true;
    }
    if (name == null || name.trim().isEmpty()) {
      request.setAttribute("message", "名前は必須です。");
      hasError = true;
    }
    int no = 0;
    int classNum = 0;
    try {
      no = Integer.parseInt(noStr);
    } catch (NumberFormatException e) {
      request.setAttribute("message", "学生番号は数値でなければなりません。");
      hasError = true;
    }
    try {
      classNum = Integer.parseInt(classNumStr);
    } catch (NumberFormatException e) {
      request.setAttribute("message", "クラス番号は数値でなければなりません。");
      hasError = true;
    }

    if (hasError) {
      // 元データを再取得してフォーム再表示
      Student student = null;
      for (Student s : dao.filter(null, null, null, schoolCd)) {
        if (s.getNo() == no) {
          student = s;
          break;
        }
      }
      request.setAttribute("student", student);
      return "student_update.jsp";
    }

    // 在学中はcheckboxなのでnullならfalse
    boolean attend = "true".equals(isAttendStr) || "on".equals(isAttendStr);

    // 更新対象学生を取得してschoolCdが合致するかチェック
    Student student = null;
    for (Student s : dao.filter(null, null, null, schoolCd)) {
      if (s.getNo() == no) {
        student = s;
        break;
      }
    }
    if (student == null) {
      request.setAttribute("message", "該当する学生が見つかりません。");
      return "student_update.jsp";
    }

    // 更新用オブジェクトに値セット
    student.setName(name);
    student.setClassNum(classNum);
    student.setAttend(attend);

    boolean success = false;
    try {
      // DAOに更新メソッドがないので、自作で実装が必要（後述）
      success = dao.update(student);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (success) {
      return "student_update_done.jsp";
    } else {
      request.setAttribute("message", "更新に失敗しました。");
      request.setAttribute("student", student);
      return "student_update.jsp";
    }
  }
}
