package com.example.ahsan.assignment5.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ahsan.assignment5.Fragment.Map;
import com.example.ahsan.assignment5.Fragment.Sunset;
import com.example.ahsan.assignment5.Fragment.Table;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Sunset();
            case 1:
                return new Table();
            case 2:
                return new Map();
        }
        return null;
    }

    @Override public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Sun Set/Rise";
            case 1:
                return "Table";
            case 2:
                return "Map";
        }
        return null;
    }
}
