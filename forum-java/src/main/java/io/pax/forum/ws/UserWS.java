package io.pax.forum.ws;

import io.pax.forum.dao.UserDao;
import io.pax.forum.domain.User;

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

        /*@POST
        public User createUser(String name) {
            User user = createUser(name);

            return user;
        }*/


}
