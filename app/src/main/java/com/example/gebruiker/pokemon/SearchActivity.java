package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void showLucario(View view) {

        String[] pokemon = {"Lucario"};

        ArrayAdapter adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, pokemon);

        ListView thelist = (ListView) findViewById(R.id.theList);
        assert thelist != null;
        thelist.setAdapter(adapter);

        thelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startNew();
            }
        });
    }

    public void startNew() {
        startActivity(new Intent(this, InfoActivity.class));
    }

    public void toForum(View view) {
        startActivity(new Intent(this, ForumActivity.class));
    }

    public void toWikia(View view) {
        startActivity(new Intent(this, WikiaActivity.class));
    }
}
