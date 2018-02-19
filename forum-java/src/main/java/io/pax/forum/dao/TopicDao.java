package io.pax.forum.dao;

import io.pax.forum.domain.Topic;
import io.pax.forum.domain.jdbc.SimpleTopic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
public class TopicDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Topic> listTopics() throws SQLException {

        List<Topic> topics = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM topic");

        while(rs.next()){
            String name = rs.getString("name");
            int id = rs.getInt("topic_id");
            topics.add(new SimpleTopic(id, name));
            System.out.println("[id:"+id +"]\t" +  name);

        }
        rs.close();
        stmt.close();
        conn.close();

        return topics;
    }
/*
    public int createWallet(int userId, String name) throws SQLException {
        // Most important stuff of your life: NEVER EVER String concatenation in JDBC
        String query = "INSERT INTO wallet (name, user_id) VALUES (?,?)"; //('test',2)
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(2, userId);
        stmt.setString(1,name);
       /*int rows = stmt.executeUpdate(query);

        if(rows!=1){
            throw new SQLException("Something wrong happened with : "+query);
        }*/
/*
        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int test_id = keys.getInt(1);

        stmt.close();
        conn.close();

        return test_id;


    }*/

    public static void main(String[] args) throws SQLException {
        TopicDao topicdao = new TopicDao();
        topicdao.listTopics();
        //userdao.createUser("Cool_name");
       // System.out.println(topicdao.listTopics());
    }


}
