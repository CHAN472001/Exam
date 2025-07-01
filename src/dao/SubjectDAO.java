package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDAO extends DAO {

    // 科目一覧
    public List<Subject> findAll(String schoolCd) throws Exception {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject WHERE school_cd = ? ORDER BY cd";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, schoolCd);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setCd(rs.getString("cd"));
                s.setName(rs.getString("name"));
                s.setSchoolCd(rs.getString("school_cd"));
                list.add(s);
            }
        }
        return list;
    }

    // 主キーで1件取得
    public Subject findByPrimaryKey(String schoolCd, String cd) throws Exception {
        String sql = "SELECT * FROM subject WHERE school_cd = ? AND cd = ?";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, schoolCd);
            st.setString(2, cd);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Subject s = new Subject();
                    s.setCd(rs.getString("cd"));
                    s.setName(rs.getString("name"));
                    s.setSchoolCd(rs.getString("school_cd"));
                    return s;
                }
            }
        }
        return null;
    }

    // 重複チェック
    public boolean exists(String cd, String schoolCd) throws Exception {
        String sql = "SELECT COUNT(*) FROM subject WHERE cd = ? AND school_cd = ?";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, cd);
            st.setString(2, schoolCd);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // 登録
    public boolean insert(Subject s) throws Exception {
        String sql = "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, s.getCd());
            st.setString(2, s.getName());
            st.setString(3, s.getSchoolCd());
            int result = st.executeUpdate();
            return result == 1;
        }
    }

    // 更新（科目名のみ）
    public boolean update(Subject s) throws Exception {
        String sql = "UPDATE subject SET name = ? WHERE school_cd = ? AND cd = ?";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, s.getName());
            st.setString(2, s.getSchoolCd());
            st.setString(3, s.getCd());
            int result = st.executeUpdate();
            return result == 1;
        }
    }
}
