package xmlxsltjdbc;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StoreSQL {
    private Config config;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public List<Integer> generate(int n) {
        List<Integer> list = new LinkedList<>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
        try (Connection con = DriverManager.getConnection(String.format("jdbc:sqlite:%s", config.getDataBase()))) {
            con.setAutoCommit(false);
            Statement stat = con.createStatement();
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS entry (field INTEGER);");
            stat.executeUpdate("DELETE FROM entry;");
            PreparedStatement pstat = con.prepareStatement("INSERT INTO entry VALUES (?);");
            for (int i = 1; i <= n; i++) {
                pstat.setInt(1, i);
                pstat.addBatch();
            }
            pstat.executeBatch();
            con.commit();
            try (ResultSet rs = stat.executeQuery("SELECT * FROM entry;")) {
                while (rs.next()) {
                    list.add(rs.getInt("field"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}
