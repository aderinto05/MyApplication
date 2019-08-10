package com.myapplication.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidpagecontrol.PageControl;
import com.myapplication.R;
import com.myapplication.main.model.ItemChild;
import com.myapplication.util.ItemTabPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailFragment extends Fragment {
    @BindView(R.id.page_control)
    PageControl pageControl;
    @BindView(R.id.pager_item)
    ViewPager pagerItem;
    Unbinder unbinder;
    private View rootView;
    ItemTabPagerAdapter tabPagerAdapter;
    List<ItemChild> itemChildList;

    public DetailFragment() {
    }

    public static DetailFragment newInstance(List<ItemChild> childs) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setItemChildList(childs);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        tabPagerAdapter = new ItemTabPagerAdapter(itemChildList, getFragmentManager());
        if(pagerItem!=null){
            pagerItem.setAdapter(tabPagerAdapter);
            pageControl.setViewPager(pagerItem);
            pageControl.setPosition(0);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    public void setItemChildList(List<ItemChild> itemChildList) {
        this.itemChildList = itemChildList;
    }
}
