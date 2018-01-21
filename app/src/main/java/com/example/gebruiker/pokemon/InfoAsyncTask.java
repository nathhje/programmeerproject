package com.example.gebruiker.pokemon;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

/**
 * Created by Gebruiker on 21-1-2018.
 */

public class InfoAsyncTask extends android.os.AsyncTask<String, Integer, String> {

    Context context;
    TabActivity tabTask;

    // context and mainAct are initialized
    public InfoAsyncTask(TabActivity tab) {
        this.tabTask = tab;
        this.context = this.tabTask.getApplicationContext();
    }

    // let's user know program is waiting for result
    protected void onPreExecute() {
        Toast.makeText(context, "retrieving data", Toast.LENGTH_SHORT).show();
    }

    // schools are retrieved
    protected String doInBackground(String... parameters) {

        try {
            // with use of HttpRequestHelper
            Log.i("ik kom hier", "denk ik wel");
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
        String ability = "";

        try {
            JSONObject pokesearch = new JSONObject(result);
            JSONArray abilities = pokesearch.getJSONArray("abilities");
            JSONObject theAbility = abilities.getJSONObject(0);
            JSONObject nextAbility = theAbility.getJSONObject("ability");
            ability = nextAbility.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // a list of the schools is made
        this.tabTask.afterInfoTask(ability);
    }
}