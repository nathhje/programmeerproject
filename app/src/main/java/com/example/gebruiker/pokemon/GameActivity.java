package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("this is", "onCreate: ");
        super.onCreate(savedInstanceState);
        Log.i("weird", "onCreate: ");
        setContentView(R.layout.activity_game);

        Log.i("what the actual fuck", "onCreate: ");

    }



    Integer clicked = 0;

    public void toWikia(View view) {
        startActivity(new Intent(this, WikiaActivity.class));
    }

    public void restart(View view) {

        TextView firstClick = (TextView) findViewById(R.id.clickOne);
        TextView secondClick = (TextView) findViewById(R.id.clickTwo);
        clicked = 0;

        firstClick.setVisibility(View.GONE);
        secondClick.setVisibility(View.GONE);
    }

    public void playTheGame(View view) {

        TextView firstClick = (TextView) findViewById(R.id.clickOne);
        TextView secondClick = (TextView) findViewById(R.id.clickTwo);
        if (clicked < 2) {
            clicked = clicked + 1;

            firstClick.setVisibility(View.VISIBLE);

            if (clicked == 2) {
                secondClick.setVisibility(View.VISIBLE);
            }
        }
    }
}
