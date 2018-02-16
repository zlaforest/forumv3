package io.pax.forum.ws;

import io.pax.forum.dao.TopicDao;
import io.pax.forum.domain.Topic;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
@Path("topics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicWS {


    @GET
    public List<Topic> getTopics() throws SQLException {
        TopicDao topicDao = new TopicDao();
        return topicDao.listTopics();
    }

   /* @POST
    /*return future wallet with an id*/
   /* public Topic createTopic(Topic topic /* sent wallet, has no idea*//*) {
       /* User user = topic.getUsers();
        if (user == null) {
            throw new NotAcceptableException("406: No user id sent");
        }
        if (wallet.getName().length() < 2) {
            throw new NotAcceptableException("406: Wallet name must have at least 2 letters");
        }
        try {
            int id = new WalletDao().createWallet(user.getId(), wallet.getName());

            User boundUser = wallet.getUser();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());

            return new FullWallet(id, wallet.getName(), simpleUser);
        } catch (SQLException e) {
            throw new ServerErrorException("Database error, sorry", 500);
        }
    }*/


}
