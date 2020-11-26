package jdbc;

import java.sql.Connection;

public interface ConnectionProvide {
    Connection getConnection();
}
