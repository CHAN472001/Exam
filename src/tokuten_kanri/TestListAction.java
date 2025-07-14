package tokuten_kanri;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
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

        // プルダウン用データ取得
        List<Integer> entYears = studentDAO.getEntYears(schoolCd);
        List<Integer> classNums = studentDAO.getClassNums(schoolCd);
        List<Subject> subjects = subjectDAO.findAll(schoolCd);

        request.setAttribute("entYears", entYears);
        request.setAttribute("classNums", classNums);
        request.setAttribute("subjects", subjects);

        if (studentNoStr != null && !studentNoStr.isEmpty()) {
            // 学生別検索
            int studentNo = Integer.parseInt(studentNoStr);
            List<Test> tests = testDAO.findByStudent(schoolCd, studentNo);

            if (tests == null || tests.isEmpty()) {
                request.setAttribute("noScoresMessage", "成績情報が存在しません。");
                request.setAttribute("testListByStudent", null);
            } else {
                request.setAttribute("testListByStudent", tests);
            }

            Student student = studentDAO.findByNo(studentNo, schoolCd);
            request.setAttribute("student", student);

            Map<String, String> subjectMap = new HashMap<>();
            for (Subject s : subjects) {
                subjectMap.put(s.getCd(), s.getName());
            }
            request.setAttribute("subjectMap", subjectMap);

            return "test_list.jsp";
        }

        if (entYearStr != null && classNumStr != null && subjectCd != null &&
            !entYearStr.isEmpty() && !classNumStr.isEmpty() && !subjectCd.isEmpty()) {

            int entYear = Integer.parseInt(entYearStr);
            int classNum = Integer.parseInt(classNumStr);

            List<Student> students = studentDAO.findByEntYearAndClass(entYear, classNum, schoolCd);
            List<Test> tests = testDAO.findByClassAndSubjectAllNos(schoolCd, entYear, classNum, subjectCd);

            if (tests == null || tests.isEmpty()) {
                request.setAttribute("noScoresMessage", "成績情報が存在しません。");
                request.setAttribute("studentsWithScores", null);
                request.setAttribute("testListByClass", null);
            } else {
                Map<Integer, Student> map = new LinkedHashMap<>();
                for (Student s : students) {
                    s.setPoints(new HashMap<>());
                    map.put(s.getNo(), s);
                }

                int maxNo = 0;
                for (Test t : tests) {
                    Student s = map.get(t.getStudentNo());
                    if (s != null && t.getNo() > 0) {
                        s.getPoints().put(t.getNo(), t.getPoint());
                        if (t.getNo() > maxNo) maxNo = t.getNo();
                    }
                }

                request.setAttribute("studentsWithScores", map.values());
                request.setAttribute("maxNo", maxNo);
                request.setAttribute("selectedSubject", subjectDAO.findByPrimaryKey(schoolCd, subjectCd));
                request.setAttribute("testListByClass", tests);
            }
            return "test_list.jsp";
        }

        return "test_list.jsp";
    }
}