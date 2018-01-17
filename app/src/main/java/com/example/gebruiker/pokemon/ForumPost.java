package com.example.gebruiker.pokemon;

/**
 * Created by Gebruiker on 17-1-2018.
 */

public class ForumPost {

    private String post;
    private String email;
    private long timeStamp;

    public ForumPost(String inPost, String commenter, long date) {

        this.post = inPost;
        this.email = commenter;
        this.timeStamp = date;

    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

