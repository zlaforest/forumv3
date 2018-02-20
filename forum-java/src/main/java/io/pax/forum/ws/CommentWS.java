package io.pax.forum.ws;

import io.pax.forum.dao.CommentDao;
import io.pax.forum.dao.UserDao;
import io.pax.forum.domain.Comment;
import io.pax.forum.domain.jdbc.SimpleComment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
@Path("comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentWS {

    @GET
    public List<Comment> getComment() throws SQLException {
       CommentDao dao = new CommentDao();
        return dao.listComments();
    }

    // JaxRS annotations
    @POST
    /* returns future topics with an id */
    public Comment createComment(SimpleComment comment){

        // Guards
        int userId = comment.getUserId();
        UserDao userDao = new UserDao();


        if(userDao == null ){
            throw new NotAcceptableException("\n406: No userDao exist\n");
        }

        if (comment.getContent().length() < 2){
            throw new NotAcceptableException("\n406: No topic name must have at least 2 letters\n");
        }

        try {

            int id = new CommentDao().createComment(userId, comment.getTopicId(), comment.getContent());

           // User boundUser = topic.getUser();
            SimpleComment simpleComment = new SimpleComment(id, userId, comment.getTopicId(),comment.getContent());
            //FullTopic fullTopic = new FullTopic(id, topic.getName(), topic.getComments());
            //fullTopic.setUser(simpleUser);
            //fullTopic.setUser(topic.getUser());

            return simpleComment;

        } catch (SQLException e) {
            throw new ServerErrorException("\nDatabase error, sorry\n", 500);
        }

    }


}
