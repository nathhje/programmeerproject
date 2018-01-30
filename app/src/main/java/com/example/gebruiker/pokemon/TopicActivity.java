package com.example.gebruiker.pokemon;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Nathalie van Sterkenburg on 11-1-2018.
 *
 * Shows a list of posts in a topic.
 */

public class TopicActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    long ID;
    String theTitle;
    ListView listPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        listPosts = findViewById(R.id.postList);

        final Intent intent = getIntent();
        ID = (long) intent.getSerializableExtra("ID");
        theTitle = intent.getStringExtra("title");

        TextView title = findViewById(R.id.topicText);
        title.setText(theTitle);

        setUpPosts();
    }

    public void setUpPosts() {

        final ArrayList<ForumPost> postArray = new ArrayList<>();

        final PostAdapter postAdapter = new PostAdapter(this, postArray);
        listPosts.setAdapter(postAdapter);

        ChildEventListener mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ForumPost aPost = dataSnapshot.getValue(ForumPost.class);
                postAdapter.add(aPost);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        Query mQuery = mDatabase.child("forum").child("posts").child(String.valueOf(ID));
        mQuery.addChildEventListener(mChildEventListener);
    }

    public void toPost(View view) {

        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("ID", ID);
        intent.putExtra("title", theTitle);

        startActivity(intent);
    }

    public void toForum(View view) {
        startActivity(new Intent(this, TabActivity.class));
    }

    public class PostAdapter extends ArrayAdapter<ForumPost> {

        PostAdapter(@NonNull Context context, ArrayList<ForumPost> posts) {
            super(context, 0, posts);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            ForumPost post = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_topiclist, parent, false);
            }

            TextView theEmail = convertView.findViewById(R.id.emailItem);
            TextView theTimeStamp = convertView.findViewById(R.id.timestampItem);
            TextView thePost = convertView.findViewById(R.id.postItem);

            Context c = getContext();
            assert post != null;
            CharSequence date = DateUtils.getRelativeDateTimeString(getContext(),
                    post.getTimeStamp(),
                    DateUtils.DAY_IN_MILLIS,
                    DateUtils.WEEK_IN_MILLIS,
                    DateUtils.FORMAT_SHOW_YEAR);

            theEmail.setText(post.getEmail());
            theTimeStamp.setText(date);
            thePost.setText(post.getPost());

            return convertView;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, TabActivity.class));
    }
}
