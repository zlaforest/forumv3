package io.pax.forum.domain;

/**
 * Created by AELION on 19/02/2018.
 */
public interface Comment {

    String getContent();
    int getId();
    int getUserId();
    int getTopicId();

}

