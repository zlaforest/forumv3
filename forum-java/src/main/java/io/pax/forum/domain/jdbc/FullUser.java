package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Topic;

import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */

public class FullUser extends SimpleUser {
    List<Topic> topics;

    public FullUser() {
        super();
    }

    public FullUser(int id, String name, List<Topic> topics) {
        super(id, name);
        this.topics = topics;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.topics;
    }

    @Override
    public List<Topic> getTopics() {
        return this.topics;
    }
}

