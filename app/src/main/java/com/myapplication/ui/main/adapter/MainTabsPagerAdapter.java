package com.myapplication.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myapplication.R;
import com.myapplication.ui.main.fragment.HomeFragment;
import com.myapplication.ui.main.fragment.ProfileFragment;


public class MainTabsPagerAdapter extends FragmentStatePagerAdapter {
    public HomeFragment homeFragment;
    public ProfileFragment profileFragment;

    private int[] imageResId = {
            R.drawable.ic_home,
            R.drawable.user
    };


    public MainTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        if(position == 0){
            homeFragment = HomeFragment.newInstance();
            return homeFragment;
        }else if(position == 1) {
            profileFragment = ProfileFragment.newInstance();
            return profileFragment;
        }
        homeFragment = HomeFragment.newInstance();
        return homeFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}


