package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.TestDAO;
import tool.Action;

public class TestRegistDoneAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String schoolCd = teacher.getSchool();

        // 学生番号と点数を配列で受け取る
        String[] studentNos = request.getParameterValues("student_no");
        String[] points = request.getParameterValues("point");

        String subjectCd = request.getParameter("subject_cd");
        int classNum = Integer.parseInt(request.getParameter("class_num"));
        int no = Integer.parseInt(request.getParameter("no"));

        TestDAO dao = new TestDAO();

        for (int i = 0; i < studentNos.length; i++) {
            int studentNo = Integer.parseInt(studentNos[i]);
            int point = Integer.parseInt(points[i]);

            Test t = new Test();
            t.setStudentNo(studentNo);
            t.setSubjectCd(subjectCd);
            t.setSchoolCd(schoolCd);
            t.setNo(no);
            t.setPoint(point);
            t.setClassNum(classNum);

            dao.upsert(t);
        }

        return "test_regist_done.jsp";
    }
}
