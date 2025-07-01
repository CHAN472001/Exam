package tokuten_kanri;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            // ログインしていなければログイン画面へ戻す
            return "login.jsp";
        }

        StudentDAO dao = new StudentDAO();
        String schoolCd = teacher.getSchool();

        // ✅ 現在の年から過去10年分の入学年度を生成
        List<Integer> entYearList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            entYearList.add(currentYear - i);
        }
        request.setAttribute("entYearList", entYearList);

        // 入力値を取得
        String noStr = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("ent_year");
        String classNumStr = request.getParameter("class_num");
        String isAttendStr = request.getParameter("is_attend");

        // 画面戻り用にセット
        request.setAttribute("no", noStr);
        request.setAttribute("name", name);
        request.setAttribute("ent_year", entYearStr);
        request.setAttribute("class_num", classNumStr);
        request.setAttribute("school_cd", schoolCd);
        request.setAttribute("is_attend", isAttendStr);

        boolean hasError = false;

        if (noStr == null || noStr.trim().isEmpty()) {
            request.setAttribute("errorNo", "学生番号を入力してください。");
            hasError = true;
        }
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("errorName", "名前を入力してください。");
            hasError = true;
        }
        if (entYearStr == null || entYearStr.trim().isEmpty()) {
            request.setAttribute("errorEntYear", "入学年度を選択してください。");
            hasError = true;
        }
        if (classNumStr == null || classNumStr.trim().isEmpty()) {
            request.setAttribute("errorClassNum", "クラス番号を入力してください。");
            hasError = true;
        }
        if (schoolCd == null || schoolCd.trim().isEmpty()) {
            request.setAttribute("errorSchoolCd", "学校コードが不明です。");
            hasError = true;
        }

        int no = 0;
        int entYear = 0;
        int classNum = 0;
        boolean attend = "true".equals(isAttendStr) || "on".equals(isAttendStr);

        try {
            if (noStr != null && !noStr.trim().isEmpty()) {
                no = Integer.parseInt(noStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorNo", "学生番号は数値で入力してください。");
            hasError = true;
        }

        try {
            if (entYearStr != null && !entYearStr.trim().isEmpty()) {
                entYear = Integer.parseInt(entYearStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorEntYear", "入学年度は数値で入力してください。");
            hasError = true;
        }

        try {
            if (classNumStr != null && !classNumStr.trim().isEmpty()) {
                classNum = Integer.parseInt(classNumStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorClassNum", "クラス番号は数値で入力してください。");
            hasError = true;
        }

        if (hasError) {
            return "student_create.jsp";
        }

        // 重複チェック
     // 重複チェックを変更 (no + schoolCd)
        if (dao.exists(no, schoolCd)) {
            request.setAttribute("errorNo", "その学生番号は既に存在します。");
            return "student_create.jsp";
        }


        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(attend);
        student.setSchoolCd(schoolCd);

        boolean result = dao.insert(student);

        if (result) {
            return "StudentComplete.action";
        } else {
            request.setAttribute("message", "登録に失敗しました。");
            return "student_create.jsp";
        }
    }
}
