package io.pax.forum.domain;

import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
public interface Topic {

    int getId();
    String getName();
    User getUser();
   // Optional<User> getUser();
    /*default Optional<User> getUser(){
        return null;
    }*/
    List<Comment> getComments();
}
