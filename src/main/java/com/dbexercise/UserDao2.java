package com.dbexercise;

import com.dbexercise.domain.User;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class UserDao2 {
//    private Connection makeConnection() throws SQLException {
//        Map<String, String> env = System.getenv();
//        Connection c = DriverManager.getConnection(
//                env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD")
//        );
//        return c;
//    }
    private ConnectionMaker connectionMaker;
    public UserDao2(){
        this.connectionMaker = new AwsConnectionMaker();
    }
    public UserDao2(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }
    public void add(User user) throws SQLException, ClassNotFoundException {

        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(id, name, password) values(?, ?, ?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2,user.getName());
        ps.setString(3, user.getPassword());

        int status = ps.executeUpdate();
        System.out.println(status);
        ps.close();
        conn.close();
        System.out.println("DB insert완료");

    }

    public User findById(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "SELECT id, name, password FROM users WHERE id = ?"
        );
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"),
                rs.getString("name"), rs.getString("password"));
        rs.close();
        ps.close();
        conn.close();
        return user;

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao2 userDao2 = new UserDao2();
        userDao2.add(new User("3", "rara", "1123"));
//        User user = userDao2.findById("0");
//        System.out.println(user.getName());


    }
}
