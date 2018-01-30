package com.example.gebruiker.pokemon;

/**
 * Created by Nathalie van Sterkenburg on 17-1-2018.
 *
 * Contains essential information on a topic.
 */

public class TopicTitle {

    private String title;
    private String email;
    private long id;

    public TopicTitle(){}

    public TopicTitle(String theTitle, String commenter, long theId) {

        this.title = theTitle;
        this.email = commenter;
        this.id = theId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
