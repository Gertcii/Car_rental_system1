package bl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionFactory {
    //соединение с бд
    private static final String DB_DRIVER = "";
    private static final String DB_URL = "";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static Connection createConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/rent_a_car",
                            "root",
                            "root");

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }
}
