package io.pax.forum.dao;

import io.pax.forum.domain.Comment;
import io.pax.forum.domain.jdbc.SimpleComment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
public class CommentDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Comment> listComments() throws SQLException {

        List<Comment> listcomments = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM comment");

        while(rs.next()){
            String name = rs.getString("content");
            int topic_id = rs.getInt("topic_id");
            int id = rs.getInt("id");
            int user_id =rs.getInt("user_id");
            listcomments.add(new SimpleComment(id, user_id, topic_id, name));
            System.out.println("[id:"+id +"]\t" +  name);

        }
        rs.close();
        stmt.close();
        conn.close();

        return listcomments;
    }

    public static void main(String[] args) throws SQLException {
        CommentDao commentdao = new CommentDao();
        commentdao.listComments();
        //userdao.createUser("Cool_name");
        // System.out.println(topicdao.listTopics());
    }


}
