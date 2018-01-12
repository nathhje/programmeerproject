package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void toGame(View view) {
        Log.i("this must be", "toGame: ");
        startActivity(new Intent(this, GameActivity.class));
    }

    public void toWikia(View view) {
        startActivity(new Intent(this, WikiaActivity.class));
    }
}
