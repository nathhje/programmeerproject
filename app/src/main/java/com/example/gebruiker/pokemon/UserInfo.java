package com.example.gebruiker.pokemon;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 25-1-2018.
 */

public class UserInfo {

    private String email;
    private String username;
    ArrayList<String> guessedPokemon;

    public UserInfo(){}

    public UserInfo(String email, String username, ArrayList<String> correctGuesses){
        this.email = email;
        this.username = username;
        this.guessedPokemon = new ArrayList<>(correctGuesses);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
