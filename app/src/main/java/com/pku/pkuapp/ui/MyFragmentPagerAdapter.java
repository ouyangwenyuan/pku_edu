package com.pku.pkuapp.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pku.pkuapp.base.BaseFragment;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<BaseFragment> list;
    String titles[] = null;

    public MyFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BaseFragment getItem(int arg0) {
        return list.get(arg0);
    }

    public String getPageTitle(int position) {
        return titles[position];
    }
}