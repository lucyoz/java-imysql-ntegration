package com.dbexercise;

import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDao2Test {

    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {
        UserDao2 userDao = new UserDao2();
        String id = "16";
        userDao.add(new User(id, "rara", "1123"));

        User user = userDao.findById(id);
        Assertions.assertEquals("rara",user.getName());
        Assertions.assertEquals("1123",user.getPassword());

    }
}