package com.example.test2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> myfragments;

    public FragAdapter(@NonNull FragmentManager fm,List<Fragment>fragments) {
        super(fm);
        myfragments=fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return myfragments.get(position);
    }

    @Override
    public int getCount() {
        return myfragments.size();
    }
}
