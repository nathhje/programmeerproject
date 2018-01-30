package com.example.gebruiker.pokemon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.net.MalformedURLException;

/**
 * Created by Nathalie van Sterkenburg on 22-1-2018.
 *
 * Sends request to HttpRequestHelper and handles output API.
 */

public class InfoAsyncTask extends android.os.AsyncTask<String, Integer, String> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private InfoActivity infoTask;
    private String typeOfSearch;

    // context and mainAct are initialized
    public InfoAsyncTask(InfoActivity info, String typeOfSearch) {
        this.infoTask = info;
        this.context = this.infoTask.getApplicationContext();
        this.typeOfSearch = typeOfSearch;
    }

    // let's user know program is waiting for result
    protected void onPreExecute() {
        Toast.makeText(context, "retrieving data", Toast.LENGTH_SHORT).show();
    }

    // schools are retrieved
    protected String doInBackground(String... parameters) {

        try {
            // with use of HttpRequestHelper
            return HttpRequestHelper.downloadFromServer(parameters);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return "";
    }

    // processes result from HttpRequestHelper
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // list of schools
        Ability theAbility = new Ability();
        Type theType = new Type();
        Pokemon thePokemon = new Pokemon();

        try {

            if(typeOfSearch.equals("ability")) { theAbility.addInfo(result); }

            if(typeOfSearch.equals("type")) { theType.addInfo(result); }

            if(typeOfSearch.equals("pokemon")) { thePokemon.addInfo(result); }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(typeOfSearch.equals("ability")) { this.infoTask.afterAbilityTask(theAbility); }

        if(typeOfSearch.equals("type")) { this.infoTask.afterTypeTask(theType); }

        if(typeOfSearch.equals("pokemon")) { this.infoTask.afterPokemonTask(thePokemon); }
    }
}
