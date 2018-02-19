package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Comment;

/**
 * Created by AELION on 19/02/2018.
 */
public class SimpleComment implements Comment{

    int id;
    String content;
    int user_id;
    int topic_id;

    public SimpleComment(){}

    public SimpleComment(int id, int user_id, int topic_id, String content) {
        this.id = id;
        this.content = content;
        this.user_id = user_id;
        this.topic_id = topic_id;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getTopicId() {
        return topic_id;
    }

    public void setTopicId(int topic_id) {
        this.topic_id = topic_id;
    }


}
