package io.pax.forum.domain;

import java.util.Optional;

/**
 * Created by AELION on 19/02/2018.
 */
public interface Topic {

    int getId();
    String getName();
    //Optional<User> getUser();
    default Optional<User> getUser(){
        return null;
    }
    //List<? extends Comment> getComments();
}
