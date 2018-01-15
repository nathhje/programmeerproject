package com.example.gebruiker.pokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class APIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        InfoAsyncTask asyncTask = new InfoAsyncTask(this);
        asyncTask.execute("pokemon/lucario");
    }

    public void afterTask(String ability) {

        Log.i("gaat goed", ability);
        TextView thisIsIt = (TextView) findViewById(R.id.searchresult);
        thisIsIt.setText("The database says Lucario's hidden ability is: " + ability);
    }
}
