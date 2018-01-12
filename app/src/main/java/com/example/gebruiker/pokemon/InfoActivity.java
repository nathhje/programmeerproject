package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


    }



    public void toInfo(View view) {startActivity(new Intent(this, InfoActivity.class));
    }

    public void toSearch(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void toForum(View view) { startActivity(new Intent(this, ForumActivity.class)); }

    public void toWikia(View view) {
        startActivity(new Intent(this, WikiaActivity.class));
    }
}
