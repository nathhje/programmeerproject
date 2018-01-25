package com.example.gebruiker.pokemon;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

/**
 * Created by Gebruiker on 25-1-2018.
 */

public class SearchAsyncTask extends android.os.AsyncTask<String, Integer, String> {

    Context context;
    TabActivity tabTask;
    String typeOfSearch;

    // context and mainAct are initialized
    public SearchAsyncTask(TabActivity tab, String typeOfSearch) {
        this.tabTask = tab;
        this.context = this.tabTask.getApplicationContext();
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

        String searchResult = " ";

        try {

            JSONObject pokesearch = new JSONObject(result);
            searchResult = pokesearch.getString("name");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.tabTask.setResult(searchResult);
    }

}
