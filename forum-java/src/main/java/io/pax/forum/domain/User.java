package io.pax.forum.domain;

import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */
public interface User {

    int getId();
    String getName();
    List<? extends Topic> getTopics();


}
