package io.pax.forum.domain.jdbc;
import io.pax.forum.domain.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
public class FullUser extends SimpleUser {

    List<Topic> topics = new ArrayList<>();

    public FullUser(){
        super();
    }

    public FullUser(int id, String name, List<Topic> wallets) {
        super(id, name);
        this.topics = wallets;
    }

    @Override
    public String toString() {
        return "(id:"+this.id+") " + this.name +":\t"+ this.topics;
    }

    @Override
    public List<Topic> getTopics() {
        return this.topics;
    }
}
