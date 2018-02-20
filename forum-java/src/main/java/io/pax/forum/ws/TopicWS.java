package io.pax.forum.ws;

import io.pax.forum.dao.TopicDao;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.FullTopic;
import io.pax.forum.domain.jdbc.SimpleUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
@Path("topics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicWS {

    @GET
    public List<Topic> getTopic() throws SQLException {
        TopicDao dao = new TopicDao();
        return dao.listTopics();

    }

    @GET
    @Path("{id}") // this is a PathParam
    public Topic getComment(@PathParam("id") int topicId) throws SQLException {
        FullTopic topic = new TopicDao().findTopicWithComments(topicId) ;
        System.out.println(topic);
        return topic;
    }

    // JaxRS annotations
    @POST
    /* returns future topic with an id */
    public FullTopic createTopic(FullTopic topic){

        // Guards
        User user = topic.getUser();

        if(user == null){
            throw new NotAcceptableException("\n406: No user Id sent\n");
        }

        if (topic.getName().length() < 2){
            throw new NotAcceptableException("\n406: No topic name must have at least 2 letters\n");
        }

        try {
            int id = new TopicDao().createTopic(user.getId(), topic.getName());

            User boundUser = topic.getUser();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());
            FullTopic fullTopic = new FullTopic(id, topic.getName(), topic.getComments());
           fullTopic.setUser(simpleUser);
           //fullTopic.setUser(topic.getUser());

            return fullTopic;

        } catch (SQLException e) {
            throw new ServerErrorException("\nDatabase error, sorry\n", 500);
        }

    }
}
