package tokuten_kanri;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String schoolCd = teacher.getSchool();

        String entYearStr = request.getParameter("ent_year");
        String classNumStr = request.getParameter("class_num");
        String subjectCd = request.getParameter("subject_cd");
        String studentNoStr = request.getParameter("student_no");

        StudentDAO studentDAO = new StudentDAO();
        SubjectDAO subjectDAO = new SubjectDAO();
        TestDAO testDAO = new TestDAO();

        // プルダウン用
        List<Integer> entYears = studentDAO.getEntYears(schoolCd);
        List<Integer> classNums = studentDAO.getClassNums(schoolCd);
        request.setAttribute("entYears", entYears);
        request.setAttribute("classNums", classNums);
        request.setAttribute("subjects", subjectDAO.findAll(schoolCd));

        if (studentNoStr != null && !studentNoStr.isEmpty()) {
            // 学生別検索
            int studentNo = Integer.parseInt(studentNoStr);
            List<Test> tests = testDAO.findByStudent(schoolCd, studentNo);
            request.setAttribute("studentTests", tests);
            return "test_list.jsp";
        }

        if (entYearStr != null && classNumStr != null && subjectCd != null &&
            !entYearStr.isEmpty() && !classNumStr.isEmpty() && !subjectCd.isEmpty()) {

            int entYear = Integer.parseInt(entYearStr);
            int classNum = Integer.parseInt(classNumStr);

            // 対象学生を取得
            List<Student> students = studentDAO.findByEntYearAndClass(entYear, classNum, schoolCd);

            // 対象成績を取得（複数回数）
            List<Test> tests = testDAO.findByClassAndSubjectAllNos(schoolCd, entYear, classNum, subjectCd);

            // 学生ごとに点数を詰める
            Map<Integer, Student> map = new LinkedHashMap<>();
            for (Student s : students) {
                s.setPoints(new HashMap<>());
                map.put(s.getNo(), s);
            }

            int maxNo = 0;
            for (Test t : tests) {
                Student s = map.get(t.getStudentNo());
                if (s != null) {
                    s.getPoints().put(t.getNo(), t.getPoint());
                    if (t.getNo() > maxNo) maxNo = t.getNo();
                }
            }

            request.setAttribute("students", map.values());
            request.setAttribute("maxNo", maxNo);

            return "test_list.jsp";
        }

        return "test_list.jsp";
    }
}
