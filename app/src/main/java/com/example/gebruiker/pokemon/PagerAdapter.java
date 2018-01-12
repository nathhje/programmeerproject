package com.example.gebruiker.pokemon;

/**
 * Created by Gebruiker on 11-1-2018.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragmentOne tab1 = new TabFragmentOne();
                return tab1;
            case 1:
                TabFragmentTwo tab2 = new TabFragmentTwo();
                return tab2;
            case 2:
                TabFragmentThree tab3 = new TabFragmentThree();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}