package io.pax.forum.dao;


import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.SimpleUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */

public class UserDao {

    JdbcConnector connector = new JdbcConnector();


    public List<User> listUsers() throws SQLException {

        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");
            users.add(new SimpleUser(id, name));

        }
        rs.close();
        stmt.close();
        conn.close();

        return users;
    }


    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO user(name) VALUES (?);";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, name);

        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        stmt.close();
        conn.close();
        System.out.println("user id created: " + id);

        return id;
    }

     public static void main(String[] args) throws SQLException {

        UserDao userdao = new UserDao();
        //userdao.createUser("Cool_name");
        //System.out.println(new UserDao().listUsers());


    }

}
