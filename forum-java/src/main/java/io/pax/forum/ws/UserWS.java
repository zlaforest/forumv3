package io.pax.forum.ws;

import io.pax.forum.dao.UserDao;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.FullUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWS {


        @GET
        public List<User> getUser() throws SQLException {
            UserDao dao = new UserDao();
            return dao.listUsers();

        }

    @GET
    @Path("{id}") // this is a path param
    public User getUser(@PathParam("id") int userId) throws SQLException {

        return new UserDao().findUserWithTopic(userId);

    }

    @POST
    /*return future user with an id*/
    public User createWallet(FullUser user /* sent wallet, has no idea*/) {
        List<Topic> topics = user.getTopics();

        if (user.getName().length() < 2) {
            throw new NotAcceptableException("406: User name must have at least 2 letters");
        }
        try {
            int id = new UserDao().createUser(user.getName());


            return new FullUser(id, user.getName(), user.getTopics());
        } catch (SQLException e) {
            throw new ServerErrorException("Database error, sorry",500);
        }
    }

}
