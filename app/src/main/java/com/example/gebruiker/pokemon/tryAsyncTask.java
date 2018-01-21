package com.example.gebruiker.pokemon;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Nathalie on 20-3-2017.
 */

public class tryAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    APIActivity infoTask;

    // context and mainAct are initialized
    public tryAsyncTask(APIActivity search) {
        this.infoTask = search;
        this.context = this.infoTask.getApplicationContext();
    }

    // let's user know program is waiting for result
    protected void onPreExecute() {
        Toast.makeText(context, "searching for lucario", Toast.LENGTH_SHORT).show();
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
            Log.i("het", "onPostExecute: ");
            // the result is put in a JSONObject
            JSONObject pokesearch = new JSONObject(result);
            Log.i("zal", "onPostExecute: ");
            // the schools are extracted from "results"
            JSONArray abilities = pokesearch.getJSONArray("abilities");
            Log.i("hier", "onPostExecute: ");
            JSONObject theAbility = abilities.getJSONObject(0);
            Log.i("wel", "onPostExecute: ");
            JSONObject nextAbility = theAbility.getJSONObject("ability");
            Log.i("ergens", "onPostExecute: ");
            ability = nextAbility.getString("name");
            Log.i("misgaan", "onPostExecute: ");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // a list of the schools is made
        this.infoTask.afterTask(ability);
    }
}