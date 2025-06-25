// EntYearDAO.java
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EntYearDAO extends DAO {

    // 全学校対象の入学年度一覧取得
    public List<String> getAllEntYears() throws Exception {
        List<String> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT ent_year FROM student ORDER BY ent_year"
        );
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("ent_year"));
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}