package io.pax.forum.dao;


import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.FullUser;
import io.pax.forum.domain.jdbc.SimpleTopic;
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM user u RIGHT JOIN topic t ON t.user_id=u.id WHERE t.id = ? ");

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

   public User findUserWithTopic(int userId) throws SQLException {

       Connection connection = connector.getConnection();
       String query = "SELECT * FROM topic t RIGHT JOIN user u ON t.user_id=u.id WHERE u.id = ?";
       PreparedStatement stmt = connection.prepareStatement(query);
       stmt.setInt(1,userId);
       ResultSet rs =  stmt.executeQuery();
       User user = null;
       // always init list
       List<Topic> topics = new ArrayList<>();

       while (rs.next()){
           String userName = rs.getString("u.name");
           System.out.println("userName:" + userName);
           user = new FullUser(userId, userName, topics );

           int topicId = rs.getInt("t.id");
           String topicName = rs.getString("t.name");

           if(topicId>0) {
               Topic topic = new SimpleTopic(topicId, topicName);
               topics.add(topic);
           }
       }
       rs.close();
       stmt.close();
       connection.close();
       return user;

   }

     public static void main(String[] args) throws SQLException {

        UserDao userdao = new UserDao();
        //userdao.createUser("Cool_name");
        // System.out.println(new UserDao().listUsers());


    }

}
