package com.demo.viewpageradvertise;

/**
 * Created by zsj on 2018/6/28.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by zsj on 2017/8/7.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return MyFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
