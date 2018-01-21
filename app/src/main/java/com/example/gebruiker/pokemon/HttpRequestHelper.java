package com.example.gebruiker.pokemon;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Nathalie on 20-3-2017.
 */

public class HttpRequestHelper {

    protected static synchronized String downloadFromServer(String... parameters) throws MalformedURLException {

        // important values are initialized
        String result = "";
        String searchterm = parameters[0];
        String link;

        // link is completed
        link = "https://pokeapi.co/api/v2/" + searchterm;
        Log.i("zou moeten", "downloadFromServer: ");
        // and turned into a url
        URL url = new URL(link);

        HttpURLConnection connect;
        Log.i("hier niet", "downloadFromServer: ");
        // url must exist
        if (url != null) {

            // data is retrieved
            try {
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");
                Log.i("stukje", "downloadFromServer: ");
                // makes sure search was succesful
                Integer responseCode = connect.getResponseCode();
                Log.i(String.valueOf(responseCode), "downloadFromServer: ");

                if (responseCode >= 200 && responseCode < 300) {
                    Log.i("bij", "downloadFromServer: ");
                    // data is retrieved
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    Log.i("beetje", "downloadFromServer: ");
                    // data is put in result
                    while((line = bReader.readLine()) != null) {
                        result += line;
                    }
                    Log.i("komen we er", "downloadFromServer: ");
                }
            }
            catch (ProtocolException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


        // result is returned
        return result;
    }
}