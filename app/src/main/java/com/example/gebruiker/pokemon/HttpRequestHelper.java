package com.example.gebruiker.pokemon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Nathalie van Sterkenburg on 11-1-2018.
 *
 * Gets information from API.
 */

public class HttpRequestHelper {

    public static synchronized String downloadFromServer(String... parameters) throws MalformedURLException {

        // important values are initialized
        StringBuilder result = new StringBuilder();
        String searchterm = parameters[0];
        String link;

        // link is completed
        link = "https://pokeapi.co/api/v2/" + searchterm;

        // and turned into a url
        URL url = new URL(link);

        HttpURLConnection connect;

        // url must exist
        if (url != null) {

            // data is retrieved
            try {
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");

                // makes sure search was succesful
                Integer responseCode = connect.getResponseCode();


                if (responseCode >= 200 && responseCode < 300) {

                    // data is retrieved
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;

                    // data is put in result
                    while((line = bReader.readLine()) != null) {
                        result.append(line);
                    }
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
        return result.toString();
    }
}