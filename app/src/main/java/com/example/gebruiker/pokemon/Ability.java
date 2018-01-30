package com.example.gebruiker.pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nathalie van Sterkenburg on 21-1-2018.
 *
 * Contains info on an ability.
 */

public class Ability {

    private String name;
    private String description;
    private String effect;
    private String generation;
    public ArrayList<String> pokemon = new ArrayList<>();

    public Ability() {}

    public void addInfo(String result) throws JSONException {

        JSONObject pokesearch = new JSONObject(result);
        this.name = pokesearch.getString("name");

        JSONArray effects = pokesearch.getJSONArray("effect_entries");
        JSONObject theEffects = effects.getJSONObject(0);
        this.description = theEffects.getString("short_effect");
        this.effect = theEffects.getString("effect");

        JSONObject theGeneration = pokesearch.getJSONObject("generation");
        this.generation = theGeneration.getString("name");

        JSONArray pokemon = pokesearch.getJSONArray("pokemon");
        for(int i = 0; i < pokemon.length(); i++) {
            JSONObject aPokemon = pokemon.getJSONObject(i);
            JSONObject thePokemon = aPokemon.getJSONObject("pokemon");
            this.pokemon.add(thePokemon.getString("name"));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }
}
