package com.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.myapplication.R;
import com.myapplication.ui.login.fragment.LoginMainFragment;
import com.myapplication.ui.main.MainActivity;
import com.myapplication.util.Constant;
import com.myapplication.util.PreferenceManagerUtil;

public class LoginActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    private LoginMainFragment loginMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragmentManager = getSupportFragmentManager();
        loginMainFragment = new LoginMainFragment();
        if(PreferenceManagerUtil.getInstance().getCurrentUser()!=null){
            openMainActivity();
        }else{
            changeFragment(Constant.loginFragment);
        }
    }

    public void changeFragment(String i) {
        if (i.equalsIgnoreCase(Constant.loginFragment)) {
            try{
                fragmentManager.beginTransaction().
                        addToBackStack(Constant.loginFragment).
                        replace(R.id.chooseVideoFrameLayout, loginMainFragment).
                        detach(loginMainFragment).attach(loginMainFragment).
                        commit();
            }catch (IllegalStateException e){
            }
        }
    }

    public void openMainActivity() {
        try {
            final Intent mainIntent = new Intent(getApplicationContext(),
                    MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(mainIntent);
        } catch (Exception e) {
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
            if(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getName().equalsIgnoreCase(Constant.loginFragment)){

            }
        }
    }
}
