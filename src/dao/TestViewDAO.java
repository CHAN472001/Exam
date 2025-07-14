package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestViewDAO extends DAO {

    // LEFT JOIN版: 成績なしも含めて全学生を取得
    public List<Test> findByClassAndSubjectAllNos(String schoolCd, int entYear, int classNum, String subjectCd) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql =
            "SELECT s.no AS student_no, t.no, t.point, t.subject_cd " +
            "FROM student s " +
            "LEFT JOIN test t ON s.no = t.student_no AND s.school_cd = t.school_cd AND t.subject_cd = ? " +
            "WHERE s.school_cd = ? AND s.ent_year = ? AND s.class_num = ? " +
            "ORDER BY s.no, t.no";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, subjectCd);
            st.setString(2, schoolCd);
            st.setInt(3, entYear);
            st.setInt(4, classNum);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Test t = new Test();
                t.setStudentNo(rs.getInt("student_no"));
                t.setNo(rs.getInt("no"));
                t.setPoint(rs.getInt("point"));
                t.setSubjectCd(subjectCd);
                list.add(t);
            }
        }
        return list;
    }

}
