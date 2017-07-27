package com.example.a15017274.p10_knowyourfacts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 15017274 on 27/7/2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;

    public FragmentAdapter (FragmentManager fm, ArrayList<Fragment> al) {
        super(fm);
        fragments = al;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
