package io.pax.forum.ws;

import io.pax.forum.dao.UserDao;
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
    @Path("{id}") // this is a PathParam
    public User getUser(@PathParam("id") int userId) throws SQLException {
        FullUser user = new UserDao().findUserWithTopics(userId);
        System.out.println(user);
        return user;
    }

    @POST
    /* returns future wallets with an id */
    public User createUser(FullUser user /**sent user, has no id*/) {

        // Guards
        //Optional<User> option = wallet.getUser();
        if (user.getName().length() < 2) {
            throw new NotAcceptableException("\n406: No user name must have at least 2 letters\n");
        }

        try {
            int id = new UserDao().createUser(user.getName());
            return new FullUser(id, user.getName(), user.getTopics());

        } catch (SQLException e) {
            // Breaks POLA
            throw new ServerErrorException("\nDatabase error, sorry\n", 500);
        }


    }

}
