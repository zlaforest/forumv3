package io.pax.forum.domain;

/**
 * Created by AELION on 16/02/2018.
 */
public interface Topic {

    int getId();
    String getName();
    User getUser();

    //List<? extends User> getUsers();

}
