package jdbc;

import java.sql.Connection;

public class ConnectionFactory {
    private final ConnectionProvide connectionProvide;

    private ConnectionFactory(ConnectionProvide connectionProvide) {
        this.connectionProvide = connectionProvide;
    }

    public Connection getConnection() {
        return connectionProvide.getConnection();
    }

    public static ConnectionFactory getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final ConnectionProvide connectionProvide = new MySQLConnectionProvideImpl();
        private static final ConnectionFactory INSTANCE = new ConnectionFactory(connectionProvide);
    }
}
