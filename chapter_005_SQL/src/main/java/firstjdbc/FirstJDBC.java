package firstjdbc;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class FirstJDBC {
    private static final Logger LOG = LoggerFactory.getLogger(FirstJDBC.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();

        String url = "jdbc:postgresql://localhost:5432/Java_a_from_z";
        String username = "postgres";
        String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM type");
            while (rs.next()) {
                System.out.println(String.format("%d %s", rs.getInt("id"), rs.getString("name")));
            } rs.close();
            st.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
