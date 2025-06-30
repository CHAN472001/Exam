package tokuten_kanri;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentListAction extends Action {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setCharacterEncoding("UTF-8");

    HttpSession session = request.getSession();
    Teacher teacher = (Teacher) session.getAttribute("teacher");
    if (teacher == null) {
      return "login.jsp";
    }
    String schoolCd = teacher.getSchool();

    String f1 = request.getParameter("f1"); // 入学年度
    String f2 = request.getParameter("f2"); // クラス番号
    String f3 = request.getParameter("f3"); // 在学中

    Map<String, String> errors = new HashMap<>();

    // クラスだけ指定されてて入学年度が未入力ならエラー
    if ((f1 == null || f1.equals("0") || f1.isEmpty()) &&
        (f2 != null && !f2.equals("0") && !f2.isEmpty())) {
      errors.put("f1", "クラスを指定する場合は入学年度も入力してください。");
    }

    request.setAttribute("errors", errors);
    request.setAttribute("f1", f1);
    request.setAttribute("f2", f2);
    request.setAttribute("f3", f3);
    request.setAttribute("filterExecuted", true);

    StudentDAO dao = new StudentDAO();

    List<Integer> entYearSet = dao.getEntYears(schoolCd);
    List<Integer> classNumSet = dao.getClassNums(schoolCd);
    request.setAttribute("ent_year_set", entYearSet);
    request.setAttribute("class_num_set", classNumSet);

    // エラーがあれば検索しない
    if (!errors.isEmpty()) {
      return "student_list.jsp";
    }

    // フィルタで学生取得
    List<Student> students = dao.filter(f1, f2, f3, schoolCd);
    request.setAttribute("students", students);

    return "student_list.jsp";
  }
}
