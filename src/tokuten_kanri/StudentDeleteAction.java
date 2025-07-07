package tokuten_kanri;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentDeleteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String schoolCd = teacher.getSchool();

        int studentNo = Integer.parseInt(request.getParameter("no"));

        StudentDAO dao = new StudentDAO();
        Student student = dao.findByNo(studentNo, schoolCd);

        request.setAttribute("student", student);
        return "student_delete.jsp";
    }
}
