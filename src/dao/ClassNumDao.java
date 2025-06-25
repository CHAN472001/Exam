// ClassNumDao.java
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import dao.DAO;

public class ClassNumDao extends DAO {

    // 全学校対象のクラス番号一覧取得
    public List<String> getAllClassNums() throws Exception {
        List<String> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT class_num FROM student ORDER BY class_num"
        );
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("class_num"));
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}
