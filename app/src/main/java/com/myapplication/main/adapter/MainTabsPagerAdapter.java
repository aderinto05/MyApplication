package com.myapplication.main.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.myapplication.R;
import com.myapplication.main.fragment.HomeFragment;
import com.myapplication.main.fragment.ProfileFragment;
import com.myapplication.util.application.Application;


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
//        if(position == 0){
//            return "HOME";
//        }else {
//            return "POFILE";
//        }
        Drawable image = Application.getContext().getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}


