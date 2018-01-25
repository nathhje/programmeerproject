package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    private ParentFragment parentFragment;
    SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), TabActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        ViewPager viewPager = findViewById(R.id.container_for_fragments);
        Log.i(String.valueOf(viewPager), "setupViewPager: ");
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), TabActivity.this);
        adapter.addFragment(new WikiaFragment(), "Wikia");
        adapter.addFragment(new ForumFragment(), "Forum");
        adapter.addFragment(new GameFragment(), "Game");
        Log.i(adapter.mFragmentTitleList.get(0), adapter.mFragmentTitleList.get(1));
        viewPager.setAdapter(adapter);
        Log.i("I dunno", "setupViewPager: ");

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
/*
        if (savedInstanceState == null) {
            // withholding the previously created fragment from being created again
            // On orientation change, it will prevent fragment recreation
            // its necessary to reserving the fragment stack inside each tab
            initScreen();

        } else {
            // restoring the previously created fragment
            // and getting the reference
            parentFragment = (ParentFragment) getSupportFragmentManager().getFragments().get(0);
        }
*/


    }

    private void setupViewPager(ViewPager viewPager) {

        Log.i("How bout it", "setupViewPager: ");
        adapter.addFragment(new WikiaFragment(), "Wikia");
        adapter.addFragment(new ForumFragment(), "Forum");
        adapter.addFragment(new GameFragment(), "Game");
        Log.i(adapter.mFragmentTitleList.get(0), adapter.mFragmentTitleList.get(1));
        viewPager.setAdapter(adapter);
        Log.i("I dunno", "setupViewPager: ");
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public SectionsPagerAdapter(FragmentManager fm, TabActivity tabActivity) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

    private void initScreen() {
        // Creating the ViewPager container fragment once
        parentFragment = new ParentFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, parentFragment)
                .commit();
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

/*
    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
    }
    */
}
