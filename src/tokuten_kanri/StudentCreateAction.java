package tokuten_kanri;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateAction extends Action {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setCharacterEncoding("UTF-8");

    StudentDAO dao = new StudentDAO();
    List<Integer> entYearList = dao.getEntYears();
    request.setAttribute("entYearList", entYearList);

    try {
      String noStr = request.getParameter("no");
      String name = request.getParameter("name");
      String entYearStr = request.getParameter("ent_year");
      String classNumStr = request.getParameter("class_num");
      String schoolCd = request.getParameter("school_cd");
      String isAttendStr = request.getParameter("is_attend");

      if (noStr == null || noStr.trim().isEmpty() ||
          name == null || name.trim().isEmpty() ||
          entYearStr == null || entYearStr.trim().isEmpty() ||
          classNumStr == null || classNumStr.trim().isEmpty() ||
          schoolCd == null || schoolCd.trim().isEmpty()) {

        request.setAttribute("message", "すべての必須項目を入力してください。");
        return "student_create.jsp";
      }

      int no = Integer.parseInt(noStr);
      int entYear = Integer.parseInt(entYearStr);
      int classNum = Integer.parseInt(classNumStr);
      boolean isAttend = "true".equals(isAttendStr) || "on".equals(isAttendStr);

      if (dao.exists(no)) {
        request.setAttribute("message", "⚠️ 学生番号が重複しています。");
        request.setAttribute("no", noStr);
        request.setAttribute("name", name);
        request.setAttribute("ent_year", entYearStr);
        request.setAttribute("class_num", classNumStr);
        request.setAttribute("school_cd", schoolCd);
        request.setAttribute("is_attend", isAttendStr);
        return "student_create.jsp";
      }

      Student student = new Student();
      student.setNo(no);
      student.setName(name);
      student.setEntYear(entYear);
      student.setClassNum(classNum);
      student.setSchoolCd(schoolCd);
      student.setAttend(isAttend);

      boolean success = dao.insert(student);

      if (success) {
        return "StudentComplete.action";
      } else {
        request.setAttribute("message", "学生情報の追加に失敗しました。");
      }

    } catch (NumberFormatException e) {
      request.setAttribute("message", "⚠️ 数字項目に誤りがあります。");
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("message", "⚠️ 予期しないエラーが発生しました。");
    }

    return "student_create.jsp";
  }
}
