package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionProvideImpl implements ConnectionProvide {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE = "my_db";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;

    @Override
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Can't connection to DB");
    }
}
