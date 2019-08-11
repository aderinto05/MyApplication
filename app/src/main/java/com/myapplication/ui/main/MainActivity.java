package com.myapplication.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.myapplication.R;
import com.myapplication.ui.main.fragment.DetailFragment;
import com.myapplication.ui.main.fragment.MainFragment;
import com.myapplication.model.ItemChild;
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
        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
            if(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getName().equalsIgnoreCase(Constant.detailItemFragment)){
                changeFragment(Constant.mainFragment);
            }
        }
    }

}
