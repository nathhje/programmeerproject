package com.example.gebruiker.pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Gebruiker on 21-1-2018.
 */

public class Pokemon {

    private String name;
    private String sprite;
    public ArrayList<String> types = new ArrayList<>();
    public ArrayList<String> abilities = new ArrayList<>();
    public ArrayList<String> baseStats = new ArrayList<>();
    public String[] statNames = {"HP: ", "Attack: ", "Defense: ", "Special Attack: ",
                                            "Special Defense: ", "Speed: "};

    public Pokemon() {}

    public void addInfo(String result) throws JSONException {

        JSONObject pokesearch = new JSONObject(result);
        this.name = pokesearch.getString("name");

        JSONObject sprites = pokesearch.getJSONObject("sprites");
        this.sprite = sprites.getString("front_default");

        JSONArray abilities = pokesearch.getJSONArray("abilities");
        for(int i = 0; i < abilities.length(); i++) {
            JSONObject ability = abilities.getJSONObject(i);
            JSONObject theAbility = ability.getJSONObject("ability");
            this.abilities.add(theAbility.getString("name"));
        }

        JSONArray types = pokesearch.getJSONArray("types");
        for(int i = 0; i < types.length(); i++) {
            JSONObject type = types.getJSONObject(i);
            JSONObject theType = type.getJSONObject("type");
            this.types.add(theType.getString("name"));
        }

        JSONArray stats = pokesearch.getJSONArray("stats");
        for(int i = 0; i < stats.length(); i++) {
            JSONObject stat = stats.getJSONObject(5-i);
            this.baseStats.add(statNames[i] + String.valueOf(stat.getInt("base_stat")));
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
