package tokuten_kanri;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            // ログインしていない場合の処理（リダイレクトなど）
            return "login.jsp";
        }

        StudentDAO dao = new StudentDAO();

        String schoolCd = teacher.getSchool();

        Set<Integer> entYearSet = new LinkedHashSet<>(dao.getEntYears(schoolCd));
        Set<Integer> classNumSet = new LinkedHashSet<>(dao.getClassNums(schoolCd));

        // パラメータ取得
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");

        f1 = (f1 == null) ? "0" : f1;
        f2 = (f2 == null) ? "0" : f2;

        List<Student> students;
        boolean filterExecuted = false;

        boolean noFilter = "0".equals(f1) && "0".equals(f2) && (f3 == null || f3.isEmpty());
        if (noFilter) {
            students = dao.findAll(schoolCd);
        } else {
            students = dao.filter(f1, f2, f3, schoolCd);
            filterExecuted = true;
        }

        request.setAttribute("students", students);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);
        request.setAttribute("filterExecuted", filterExecuted);  // フラグ追加

        return "student_list.jsp";
    }
}
