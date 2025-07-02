package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDAO extends DAO {

    // 科目別：全回数 LEFT JOIN で全学生を拾う
    public List<Test> findByClassAndSubjectAllNos(String schoolCd, int entYear, int classNum, String subjectCd) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql =
            "SELECT s.no AS student_no, s.name AS student_name, s.ent_year, s.class_num, " +
            "t.subject_cd, t.no, t.point " +
            "FROM student s " +
            "LEFT JOIN test t ON s.no = t.student_no AND t.school_cd = s.school_cd AND t.subject_cd = ? " +
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
                t.setStudentName(rs.getString("student_name"));
                t.setEntYear(rs.getInt("ent_year"));
                t.setClassNum(rs.getInt("class_num"));
                t.setSubjectCd(subjectCd);
                t.setNo(rs.getInt("no"));
                t.setPoint(rs.getInt("point"));
                list.add(t);
            }
        }
        return list;
    }

    // 学生別
    public List<Test> findByStudent(String schoolCd, int studentNo) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql =
            "SELECT * FROM test WHERE school_cd = ? AND student_no = ? ORDER BY subject_cd, no";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, schoolCd);
            st.setInt(2, studentNo);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Test t = new Test();
                t.setStudentNo(rs.getInt("student_no"));
                t.setSubjectCd(rs.getString("subject_cd"));
                t.setSchoolCd(rs.getString("school_cd"));
                t.setNo(rs.getInt("no"));
                t.setPoint(rs.getInt("point"));
                t.setClassNum(rs.getInt("class_num"));
                list.add(t);
            }
        }
        return list;
    }

    // 成績登録 or 更新
    public void upsert(Test t) throws Exception {
        String sql =
            "MERGE INTO test KEY (student_no, subject_cd, school_cd, no) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, t.getStudentNo());
            st.setString(2, t.getSubjectCd());
            st.setString(3, t.getSchoolCd());
            st.setInt(4, t.getNo());
            st.setInt(5, t.getPoint());
            st.setInt(6, t.getClassNum());

            st.executeUpdate();
        }
    }

    // 旧: 条件付き取得 (不要なら消してOK)
    public List<Test> findByCondition(String schoolCd, int entYear, int classNum, String subjectCd, int no) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql =
            "SELECT s.no AS student_no, s.name AS student_name, s.ent_year, s.class_num, " +
            "COALESCE(t.point, 0) AS point " +
            "FROM student s " +
            "LEFT JOIN test t ON s.no = t.student_no AND t.subject_cd = ? AND t.school_cd = ? AND t.no = ? " +
            "WHERE s.school_cd = ? AND s.ent_year = ? AND s.class_num = ? " +
            "ORDER BY s.no";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, subjectCd);
            st.setString(2, schoolCd);
            st.setInt(3, no);
            st.setString(4, schoolCd);
            st.setInt(5, entYear);
            st.setInt(6, classNum);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Test t = new Test();
                t.setStudentNo(rs.getInt("student_no"));
                t.setStudentName(rs.getString("student_name"));
                t.setEntYear(rs.getInt("ent_year"));
                t.setClassNum(rs.getInt("class_num"));
                t.setSubjectCd(subjectCd);
                t.setSchoolCd(schoolCd);
                t.setNo(no);
                t.setPoint(rs.getInt("point"));
                list.add(t);
            }
        }
        return list;
    }
}
