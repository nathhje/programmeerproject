package com.example.gebruiker.pokemon;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.net.MalformedURLException;

/**
 * Created by Gebruiker on 22-1-2018.
 */

public class TypeAsyncTask extends android.os.AsyncTask<String, Integer, String> {

    Context context;
    TabActivity tabTask;
    InfoFragment fragment;

    // context and mainAct are initialized
    public TypeAsyncTask(TabActivity tab, InfoFragment fragment) {
        this.tabTask = tab;
        this.context = this.tabTask.getApplicationContext();
        this.fragment = fragment;
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

        Type theType = new Type();

        try {

            theType.addInfo(result);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.tabTask.typeAsyncTask(theType, fragment);

    }

}
