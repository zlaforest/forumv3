package io.pax.forum.dao;

import io.pax.forum.domain.Comment;
import io.pax.forum.domain.jdbc.SimpleComment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
public class CommentDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Comment> listComments() throws SQLException {

        List<Comment> comments = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM comment");

        while(rs.next()){
            String name = rs.getString("content");
            int topic_id = rs.getInt("topic_id");
            int id = rs.getInt("id");
            int user_id =rs.getInt("user_id");
            comments.add(new SimpleComment(id, user_id, topic_id, name));
            System.out.println("[id:"+id +"]\t" +  name);

        }
        rs.close();
        stmt.close();
        conn.close();

        return comments;
    }

    public SimpleComment findById(int id) throws SQLException {
        String query = "SELECT * FROM comment WHERE id= ?";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        SimpleComment simpleComment=null;
        while (rs.next()) {
            simpleComment = new SimpleComment(rs.getInt("id"), rs.getInt("topic_id"), rs.getInt("user_id"), rs.getString("content"));
            //Comment comment = simpleComment; // CAST
            System.out.println("For id " + id + ":\t" + rs.getString("content"));
        }

        stmt.close();
        conn.close();

        return simpleComment;

    }

    public int createComment(int userId, int topicId, String comment) throws SQLException {
        // Most important stuff of your life: NEVER EVER String concatenation in JDBC
        String query = "INSERT INTO comment (user_id, topic_id, content) VALUES (?,?,?)"; //('test',2)
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, userId);
        stmt.setInt(2, topicId);
        stmt.setString(3, comment);
       /*int rows = stmt.executeUpdate(query);
        if(rows!=1){
            throw new SQLException("Something wrong happened with : "+query);
        }*/

        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int test_id = keys.getInt(1);
        System.out.println("comment id created: " + test_id);

        stmt.close();
        conn.close();

        return test_id;
    }

    public static void main(String[] args) throws SQLException {
        CommentDao commentdao = new CommentDao();
        //commentdao.createComment(4,5,"Test_Winter_is_comming");
        commentdao.listComments();
        // System.out.println(topicdao.listTopics());
        SimpleComment comment = commentdao.findById(9);
    }


}
