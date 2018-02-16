package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.User;

/**
 * Created by AELION on 16/02/2018.
 */
public class FullTopic extends SimpleTopic{
    SimpleUser user;
    //empty constructor with no real sense
    // needed for java ee bindings tecgnology

    public FullTopic() {
        super();
    }

    public FullTopic(int id, String name, SimpleUser user) {
        super(id, name);
        this.user = user;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

}
