package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;

import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */
public class SimpleUser implements User {

    int id;
    String name;

    public SimpleUser(){

    }

    public SimpleUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Topic> getTopics() {
        return null;
    }
}
