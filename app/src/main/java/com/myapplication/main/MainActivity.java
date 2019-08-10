package com.myapplication.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;

import com.myapplication.main.fragment.DetailFragment;
import com.myapplication.main.fragment.MainFragment;
import com.myapplication.R;
import com.myapplication.main.model.ItemChild;
import com.myapplication.util.Constant;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainFragment mainFragment;
    private DetailFragment detailFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        mainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();
        changeFragment(Constant.mainFragment);
    }

    public void showDetailItem(List<ItemChild> childs){
        detailFragment = DetailFragment.newInstance(childs);
        changeFragment(Constant.detailItemFragment);
    }

    public void changeFragment(String i) {
        if (i.equalsIgnoreCase(Constant.mainFragment)) {
            try{
                fragmentManager.beginTransaction().
                        addToBackStack(Constant.mainFragment).
                        replace(R.id.chooseVideoFrameLayout, mainFragment).
                        detach(mainFragment).attach(mainFragment).
                        commit();
            }catch (IllegalStateException e){
            }
        }else if (i.equalsIgnoreCase(Constant.detailItemFragment)) {
            try{
                isShowActionBar(false);
                fragmentManager.beginTransaction().
                        addToBackStack(Constant.detailItemFragment).
                        replace(R.id.chooseVideoFrameLayout, detailFragment).
                        detach(detailFragment).attach(detailFragment).
                        commit();
            }catch (IllegalStateException e){
            }
        }
    }

    @Override
    public void onBackPressed() {
        getSupportActionBar().hide();
        getSupportActionBar().show();
        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
            if(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getName().equalsIgnoreCase(Constant.detailItemFragment)){
                changeFragment(Constant.mainFragment);
                isShowActionBar(true);
            }
        }
    }

    public void isShowActionBar(boolean isShow){
        if(isShow)
            getSupportActionBar().show();
        else
            getSupportActionBar().hide();
    }

}
