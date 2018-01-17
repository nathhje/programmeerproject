package com.example.gebruiker.pokemon;

/**
 * Created by Gebruiker on 17-1-2018.
 */

public class TopicTitle {

    private String title;
    private String email;

    public TopicTitle(String theTitle, String commenter) {

        this.title = theTitle;
        this.email = commenter;

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
}
