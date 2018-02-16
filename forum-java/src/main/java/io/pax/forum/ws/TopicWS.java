package io.pax.forum.ws;

import io.pax.forum.dao.TopicDao;
import io.pax.forum.dao.UserDao;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.FullTopic;
import io.pax.forum.domain.jdbc.SimpleUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
@Path("(id)/topics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicWS {


    @GET
    public List<Topic> getTopics() throws SQLException {
        TopicDao topicDao = new TopicDao();
        return topicDao.listTopics();
    }

    @GET
    @Path("{id}") // this is a path param
    public Topic getTopic(@PathParam("id") int topicId) throws SQLException {

        return new TopicDao().findTopicWithUser(topicId);

    }

    @POST
    public Topic createTopic(FullTopic topic){

        // Guards
        User user = topic.getUser();

        if(user == null){
            // 400x : navigator sent wrong information
            throw new NotAcceptableException("\n406: No user Id sent\n");
        }

        if (topic.getName().length() < 2){
            throw new NotAcceptableException("\n406: No wallet name must have at least 2 letters\n");
        }

        try {
            int id = new TopicDao().createTopic(user.getId(), topic.getName());

            User boundUser = topic.getUser();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());
            return new FullTopic(id,  topic.getName(), simpleUser);

        } catch (SQLException e) {
            // Breaks POLA
            throw new ServerErrorException("\nDatabase error, sorry\n", 500);
        }

    }


}
