package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
    }

    public void toTopic(View view) { startActivity(new Intent(this, TopicActivity.class)); }

    public void toWikia(View view) {
        startActivity(new Intent(this, WikiaActivity.class));
    }

    public void toNew(View view) {
        startActivity(new Intent(this, NewActivity.class));
    }
}
