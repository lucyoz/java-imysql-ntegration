package com.dbexercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class LocalUserDaoImpl extends UserDaoAbstract{
    @Override
    public Connection makeConnection() throws SQLException {
        Map<String, String> env = System.getenv();
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/likelion-db","root","12345678"
        );
        return c;
    }
}
