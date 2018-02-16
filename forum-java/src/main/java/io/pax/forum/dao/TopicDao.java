package io.pax.forum.dao;

import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.FullTopic;
import io.pax.forum.domain.jdbc.SimpleTopic;
import io.pax.forum.domain.jdbc.SimpleUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public class TopicDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Topic> listTopics() throws SQLException {

        List<Topic> topics = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM topic t  JOIN user u ON t.user_id=u.id");

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("topic_id");
            topics.add(new SimpleTopic(id, name));

        }
        rs.close();
        stmt.close();
        conn.close();

        return topics;
    }

    public int createTopic(int userId, String name) throws SQLException {
        // most important stuff : never ever String concatenation in JDBC
        String query = "INSERT INTO topic(name, user_id) VALUES(?,?)";
        //query= "INSERT INTO wallet (name, user_id) VALUES (`test`,2)";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, name);
        stmt.setInt(2, userId);

        stmt.executeUpdate();


        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        stmt.close();
        conn.close();

        return id;

    }

    public User findTopicWithUser(int topicId) throws SQLException {

        Connection connection = connector.getConnection();
        String query = "SELECT * FROM user u RIGHT JOIN topic t ON t.user_id=u.id WHERE t.id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,topicId);
        ResultSet rs =  stmt.executeQuery();
        Topic topic = null;
        // always init list
        List<User> users = new ArrayList<>();

        while (rs.next()){
            String topicName = rs.getString("t.name");
            System.out.println("topicName:" + topicName);
            topic = new FullTopic(topicId, topicName, users);

            int userId = rs.getInt("u.id");
            String userName = rs.getString("u.name");

            if(userId>0) {
                User user = new SimpleUser(userId, userName);
                users.add(user);
            }
        }
        rs.close();
        stmt.close();
        connection.close();
        return topic;

    }

    public static void main(String[] args) throws SQLException {
        TopicDao topicdao = new TopicDao();
        //topicdao.createTopic(1, "Cool_name_topic");
       // System.out.println(new TopicDao().listTopics());
    }
}
