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

        /*@POST
        public User createUser(String name) {
            User user = createUser(name);

            return user;
        }*/


}
