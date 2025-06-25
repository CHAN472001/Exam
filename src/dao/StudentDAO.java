package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDAO extends DAO {

    // 学生リストをフィルターして取得
    public List<Student> filter(String f1, String f2, String f3) throws Exception {
        List<Student> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // 入学年度フィルター
        if (f1 != null && !f1.equals("0")) {
            sql.append(" AND ent_year = ?");
            params.add(Integer.parseInt(f1));
        }

        // クラス番号フィルター
        if (f2 != null && !f2.equals("0")) {
            sql.append(" AND class_num = ?");
            params.add(Integer.parseInt(f2));
        }

        // 在学中フィルター
        if (f3 != null && f3.equals("t")) {
            sql.append(" AND is_attend = true");
        }

        sql.append(" ORDER BY ent_year, class_num, no");

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getInt("no"));
                s.setName(rs.getString("name"));
                s.setEntYear(rs.getInt("ent_year"));
                s.setClassNum(rs.getInt("class_num"));
                s.setAttend(rs.getBoolean("is_attend"));
                list.add(s);
            }
        }

        return list;
    }

    // 入学年度一覧の取得
    public List<Integer> getEntYears() throws Exception {
        List<Integer> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "SELECT DISTINCT ent_year FROM student ORDER BY ent_year");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("ent_year"));
            }
        }

        return list;
    }

    // クラス番号一覧の取得
    public List<Integer> getClassNums() throws Exception {
        List<Integer> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "SELECT DISTINCT class_num FROM student ORDER BY class_num");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("class_num"));
            }
        }

        return list;
    }

    // 全学生を取得
    public List<Student> findAll() throws Exception {
        List<Student> list = new ArrayList<>();

        String sql = "SELECT * FROM student";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getInt("no"));
                s.setName(rs.getString("name"));
                s.setEntYear(rs.getInt("ent_year"));
                s.setClassNum(rs.getInt("class_num"));
                s.setAttend(rs.getBoolean("is_attend"));
                s.setSchoolCd(rs.getString("school_cd"));

                System.out.println("読み込み成功: " + s.getNo() + " " + s.getName()); // ←追加

                list.add(s);
            }
        }

        return list;
    }

    // 学生の追加（insert）
    public boolean insert(Student s) throws Exception {
        String sql = "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, s.getNo());
            st.setString(2, s.getName());
            st.setInt(3, s.getEntYear());
            st.setInt(4, s.getClassNum());
            st.setBoolean(5, s.isAttend());
            st.setString(6, s.getSchoolCd());

            int result = st.executeUpdate();
            return result == 1;
        }
    }
}
