package tokuten_kanri;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String schoolCd = teacher != null ? teacher.getSchool() : null;

        if (schoolCd == null || schoolCd.isEmpty()) {
            request.setAttribute("message", "学校コードが見つかりません（ログイン情報が無効です）。");
            return "test_regist.jsp";
        }

        // 必ず科目プルダウン用データを渡す
        SubjectDAO subjectDao = new SubjectDAO();
        List<Subject> subjects = subjectDao.findAll(schoolCd);
        request.setAttribute("subjects", subjects);

        // 絞り込み条件のプルダウン
        StudentDAO studentDao = new StudentDAO();
        List<Integer> entYears = studentDao.getEntYears(schoolCd);
        List<Integer> classNums = studentDao.getClassNums(schoolCd);
        request.setAttribute("entYears", entYears);
        request.setAttribute("classNums", classNums);

        // パラメータ取得
        String entYearStr = request.getParameter("ent_year");
        String classNumStr = request.getParameter("class_num");
        String subjectCd = request.getParameter("subject_cd");
        String noStr = request.getParameter("no");

        int entYear = 0;
        int classNum = 0;
        int no = 1;

        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (classNumStr != null && !classNumStr.isEmpty()) {
            classNum = Integer.parseInt(classNumStr);
        }
        if (noStr != null && !noStr.isEmpty()) {
            no = Integer.parseInt(noStr);
        }

        request.setAttribute("selectedEntYear", entYear);
        request.setAttribute("selectedClassNum", classNum);
        request.setAttribute("selectedSubjectCd", subjectCd);
        request.setAttribute("selectedNo", no);

        // 必須条件が揃ってなければ終了（ここでもプルダウンは出る）
        if (entYear == 0 || classNum == 0 || subjectCd == null || subjectCd.isEmpty()) {
            return "test_regist.jsp";
        }

        // 成績データ取得
        TestDAO testDao = new TestDAO();
        List<Test> testList = testDao.findByCondition(schoolCd, entYear, classNum, subjectCd, no);
        request.setAttribute("testList", testList);

        return "test_regist.jsp";
    }
}
