package com.myapplication.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myapplication.main.model.ItemChild;

import java.util.ArrayList;
import java.util.List;



public class ItemTabPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ItemTabPagerAdapter(List<ItemChild> images, FragmentManager fm) {  //Bitmap bmp,
        super(fm);
        for(int i =0 ; i< images.size() ; i++){
            mFragmentList.add(ItemGalleryFragment.newInstance(images.get(i)));
        }
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
