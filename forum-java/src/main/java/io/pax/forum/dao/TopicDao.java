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




    public static void main(String[] args) throws SQLException {
        TopicDao topicdao = new TopicDao();
        topicdao.listTopics();
        //userdao.createUser("Cool_name");
       // System.out.println(topicdao.listTopics());
    }


}
