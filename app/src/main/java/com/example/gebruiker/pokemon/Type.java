package com.example.gebruiker.pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 21-1-2018.
 */

public class Type {

    private String name;
    private ArrayList<String> halfTo = new ArrayList<>();
    private ArrayList<String> noTo = new ArrayList<>();
    private ArrayList<String> doubleTo = new ArrayList<>();
    private ArrayList<String> halfFrom = new ArrayList<>();
    private ArrayList<String> noFrom = new ArrayList<>();
    private ArrayList<String> doubleFrom = new ArrayList<>();
    private ArrayList<String> pokemon = new ArrayList<>();

    public Type() {}

    public void addInfo(String result) throws JSONException {

        JSONObject pokesearch = new JSONObject(result);
        this.name = pokesearch.getString("name");

        JSONObject damageRelations = pokesearch.getJSONObject("damage_relations");
        JSONArray halfFrom = damageRelations.getJSONArray("half_damage_from");
        for(int i = 0; i < halfFrom.length(); i++) {
            JSONObject half = halfFrom.getJSONObject(i);
            this.halfFrom.add(half.getString("name"));
        }

        JSONArray noneFrom = damageRelations.getJSONArray("no_damage_from");
        for(int i = 0; i < noneFrom.length(); i++) {
            JSONObject none = noneFrom.getJSONObject(i);
            this.noFrom.add(none.getString("name"));
        }

        JSONArray doubleFrom = damageRelations.getJSONArray("double_damage_from");
        for(int i = 0; i < doubleFrom.length(); i++) {
            JSONObject doubleDamage = doubleFrom.getJSONObject(i);
            this.doubleFrom.add(doubleDamage.getString("name"));
        }

        JSONArray halfTo = damageRelations.getJSONArray("half_damage_to");
        for(int i = 0; i < halfTo.length(); i++) {
            JSONObject half = halfTo.getJSONObject(i);
            this.halfTo.add(half.getString("name"));
        }

        JSONArray noneTo = damageRelations.getJSONArray("no_damage_to");
        for(int i = 0; i < noneTo.length(); i++) {
            JSONObject none = noneTo.getJSONObject(i);
            this.noTo.add(none.getString("name"));
        }

        JSONArray doubleTo = damageRelations.getJSONArray("double_damage_to");
        for(int i = 0; i < doubleTo.length(); i++) {
            JSONObject doubleDamage = doubleTo.getJSONObject(i);
            this.doubleTo.add(doubleDamage.getString("name"));
        }

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
}
