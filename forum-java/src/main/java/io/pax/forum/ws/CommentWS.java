package io.pax.forum.ws;

import io.pax.forum.dao.CommentDao;
import io.pax.forum.domain.Comment;

import javax.ws.rs.GET;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
public class CommentWS {

    @GET
    public List<Comment> getComment() throws SQLException {
       CommentDao dao = new CommentDao();
        return dao.listComments();

    }




}
