package io.pax.forum.dao;

import io.pax.forum.domain.Comment;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.jdbc.FullTopic;
import io.pax.forum.domain.jdbc.SimpleComment;
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

    public FullTopic findTopicWithComments(int topicId) throws SQLException {
        Connection conn = this.connector.getConnection();

        String query = "SELECT * FROM topic t JOIN comment c ON t.topic_id=c.topic_id WHERE t.topic_id =?";
        System.out.println(query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, topicId);
        ResultSet rs = stmt.executeQuery();

        FullTopic topic = null;
        //user = null;
        // pro tip: always init lists
        List<Comment> comments= new ArrayList<>();

        while (rs.next()) {
            String topicName = rs.getString("t.name");
            System.out.println("userName: "+topicName);

            //user.setName(userName);
            // user.setId(userId);

            topic = new FullTopic(topicId, topicName, comments);

            int userId =rs.getInt("c.user_id");
            int commentId =rs.getInt("c.id");
            String commentName = rs.getString("c.content");

            if(topicId>0){
                Comment comment = new SimpleComment(commentId,userId,topicId, commentName);
                comments.add(comment);
                System.out.println("[id:"+commentId +"]\t" +  commentName);
            }

        }

        stmt.close();
        conn.close();

        return topic;

    }

    public int createTopic(int userId, String name) throws SQLException {
        // Most important stuff of your life: NEVER EVER String concatenation in JDBC
        String query = "INSERT INTO topic (name, user_id) VALUES (?,?)"; //('test',2)
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(2, userId);
        stmt.setString(1,name);
       /*int rows = stmt.executeUpdate(query);

        if(rows!=1){
            throw new SQLException("Something wrong happened with : "+query);
        }*/

        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int test_id = keys.getInt(1);
        System.out.println("topic id created: " + test_id);

        stmt.close();
        conn.close();

        return test_id;
    }

    public void updateTopic(int topicId, String newName) throws SQLException {
        String query = "UPDATE topic SET name = ? WHERE topic_id = ? ";
        //System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setInt(2, topicId);
        statement.setString(1,newName);
        statement.executeUpdate();
        statement.close();
        conn.close();

        System.out.println("topic id updated: " + topicId);

    }


    public static void main(String[] args) throws SQLException {
        TopicDao topicdao = new TopicDao();
        //topicdao.findTopicWithComments(2);
        //topicdao.createTopic(5, "Cool_Topic_User5");
        topicdao.updateTopic(13, "Updated Topic number 13" );
        System.out.println(topicdao.listTopics());
    }


}
