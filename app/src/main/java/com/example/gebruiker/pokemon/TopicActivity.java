package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
    }

    public void toPost(View view) {
        startActivity(new Intent(this, PostActivity.class));
    }

    public void toWikia(View view) {
        startActivity(new Intent(this, WikiaActivity.class));
    }

    public void toForum(View view) {
        startActivity(new Intent(this, ForumActivity.class));
    }
}
