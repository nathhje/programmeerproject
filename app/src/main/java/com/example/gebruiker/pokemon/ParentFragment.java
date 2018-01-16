package com.example.gebruiker.pokemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gebruiker on 16-1-2018.
 */

public class ParentFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.container);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        setupViewPager(mViewPager);

        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        Log.i("How bout it", "setupViewPager: ");
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getChildFragmentManager());
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


        public SectionsPagerAdapter(FragmentManager fm) {
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
}

