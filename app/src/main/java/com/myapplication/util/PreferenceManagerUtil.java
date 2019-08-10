package com.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myapplication.model.User;
import com.myapplication.util.application.Application;

import java.lang.reflect.Type;

public class PreferenceManagerUtil {

    private static String TAG = "PreferenceManagerUtil";
    private static PreferenceManagerUtil instance;
    private static SharedPreferences PREF = null;
    private static SharedPreferences.Editor PREF_EDITOR = null;
    private static final String CURRENTUSER = "current_user";
    private static User currentUser;

    public static PreferenceManagerUtil getInstance() {
        if (instance == null) {
            synchronized (PreferenceManagerUtil.class) {
                if (instance == null) {
                    instance = new PreferenceManagerUtil();
                }

                PREF = Application.getAppContext().getSharedPreferences("myapplication", Context.MODE_PRIVATE);
                PREF_EDITOR = PREF.edit();

                if (PREF != null) {
                    Log.v(TAG, "pref available");
                } else {
                    Log.v(TAG, "pref null");
                }
                if (PREF_EDITOR != null) {
                    Log.v(TAG, "pref editor available");
                } else {
                    Log.v(TAG, "pref editor null");
                }
            }
        }
        return instance;
    }

    public boolean saveCurrentUser(User user) {
        currentUser = user;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Application.getContext());
        Editor e = prefs.edit();
        try{
            if (user == null) {
                e.remove(CURRENTUSER);
                //return e.commit();
                e.apply();
                return false;
            }
        }catch(RuntimeException ex){
            Log.d(TAG, "error saveCurrentUser remove user " + ex.getMessage());
        }
        try{
            Gson gson = new Gson();
            // convert to json first
            String userString = gson.toJson(user);
            // the save it as string
            e.putString(CURRENTUSER, userString);
            e.apply();
        }catch(RuntimeException ex){
            Log.d(TAG, "error saveCurrentUser" + ex.getMessage());
        }

        return true;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }

        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Application.getContext());

            // get saved user json as string
            String s = prefs.getString(CURRENTUSER, null);
            if (s == null || s.equalsIgnoreCase("null") || s.isEmpty()) {
                return null;
            }
            // Create Gson
            Gson gson = new Gson();

            Type type = new TypeToken<User>() {
            }.getType();

            // convert the json to User
            currentUser = gson.fromJson(s, type);
            return currentUser;
        } catch (Exception e) {
        }
        return null;
    }
}
