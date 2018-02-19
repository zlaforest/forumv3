package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Comment;
//import io.pax.forum.domain.SimpleTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
public class FullTopic extends SimpleTopic {

    List<Comment> comments = new ArrayList<>();

    public FullTopic(){
        super();
    }

    public FullTopic(int id, String name, List<Comment> comments) {
        super(id, name);
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "(id:"+this.id+") " + this.name +":\t"+ this.comments;
    }

    @Override
    public List<Comment> getComments() {
        return this.comments;
    }
}
