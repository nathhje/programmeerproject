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

        StringBuilder result = new StringBuilder();
        String searchterm = parameters[0];
        String link;

        link = "https://pokeapi.co/api/v2/" + searchterm;

        URL url = new URL(link);

        HttpURLConnection connect;

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
        return result.toString();
    }
}