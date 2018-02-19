package io.pax.forum.ws;

import io.pax.forum.dao.TopicDao;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.jdbc.FullTopic;

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
}
