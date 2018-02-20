package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Comment;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;

import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
public class SimpleTopic implements Topic{

    int id;
    String name;

    // Useless constructor, but for Java EE
    public SimpleTopic(){
    }

    public SimpleTopic(int id, String name) {
        this.id = id;
        this.name = name;
    }

   /* public SimpleUser simpleUser(){
        return this.user;
    }*/

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /*@Override
    public Optional<User> getUser() {
        return Optional.empty();
        //return null;
    }*/

    @Override
    public User getUser(){
        return null;
    }

    @Override
    public List<Comment> getComments() {
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setName(String name) {this.name = name;}
}
