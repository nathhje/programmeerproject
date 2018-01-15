package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class WikiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wikia);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    public void toInfo(View view) {
        startActivity(new Intent(this, InfoActivity.class));
    }

    public void toSearch(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void toForum(View view) { startActivity(new Intent(this, ForumActivity.class)); }

    public void toIntro(View view) {
        startActivity(new Intent(this, IntroActivity.class));
    }

    public void toAPI(View view) { startActivity(new Intent(this, APIActivity.class));}
}
