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

    // 入学年度リストは必ずセット（例外外でも必要）
    StudentDAO dao = new StudentDAO();
    List<Integer> entYearList = dao.getEntYears();
    request.setAttribute("entYearList", entYearList);

    try {
      // パラメータ取得
      String noStr = request.getParameter("no");
      String name = request.getParameter("name");
      String entYearStr = request.getParameter("ent_year");
      String classNumStr = request.getParameter("class_num");
      String schoolCd = request.getParameter("school_cd");
      String isAttendStr = request.getParameter("is_attend");

      // 必須項目の空チェック
      if (noStr == null || noStr.trim().isEmpty() ||
          name == null || name.trim().isEmpty() ||
          entYearStr == null || entYearStr.trim().isEmpty() ||
          classNumStr == null || classNumStr.trim().isEmpty() ||
          schoolCd == null || schoolCd.trim().isEmpty()) {

          request.setAttribute("message", "すべての必須項目を入力してください。");
          return "student_create.jsp";
      }

      // 数値変換
      int no = Integer.parseInt(noStr);
      int entYear = Integer.parseInt(entYearStr);
      int classNum = Integer.parseInt(classNumStr);
      boolean isAttend = "true".equals(isAttendStr) || "on".equals(isAttendStr);

      // Studentオブジェクトにセット
      Student student = new Student();
      student.setNo(no);
      student.setName(name);
      student.setEntYear(entYear);
      student.setClassNum(classNum);
      student.setSchoolCd(schoolCd);
      student.setAttend(isAttend);

      // DAOで登録
      boolean success = dao.insert(student);

      if(success) {
        request.setAttribute("message", "学生情報を追加しました。");
        // フォーム初期化
        request.setAttribute("no", "");
        request.setAttribute("name", "");
        request.setAttribute("ent_year", "");
        request.setAttribute("class_num", "");
        request.setAttribute("school_cd", "");
        request.setAttribute("is_attend", "");
      } else {
        request.setAttribute("message", "学生情報の追加に失敗しました。");
      }

    } catch (NumberFormatException e) {
      request.setAttribute("message", "数字項目に誤りがあります。");
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("message", "予期しないエラーが発生しました。");
    }

    return "student_create.jsp";
  }
}
