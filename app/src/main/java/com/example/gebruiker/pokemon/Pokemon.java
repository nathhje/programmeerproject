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
    private ArrayList<String> types = new ArrayList<>();
    private ArrayList<String> abilities = new ArrayList<>();
    private ArrayList<Integer> baseStats = new ArrayList<>();

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
            JSONObject stat = stats.getJSONObject(i);
            this.baseStats.add(stat.getInt("base_stat"));
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
