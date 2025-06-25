package chapter24;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginAction extends Action{
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session=request.getSession();

		String login=request.getParameter("login");
		String password=request.getParameter("password");
		TeacherDAO dao=new TeacherDAO();
		Teacher teacher=dao.search(login, password);

		if (teacher!=null){
			session.setAttribute("teacher", teacher);
			return "login-out.jsp";
		}
		return "login-error.jsp";
	}

}