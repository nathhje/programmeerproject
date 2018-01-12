package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }

    public void toTopic(View view) {
        startActivity(new Intent(this, TopicActivity.class));
    }

    public void toWikia(View view) {
        startActivity(new Intent(this, WikiaActivity.class));
    }

    public void toForum(View view) {
        startActivity(new Intent(this, ForumActivity.class));
    }
}
