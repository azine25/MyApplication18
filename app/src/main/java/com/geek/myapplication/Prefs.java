package com.geek.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;
    private static Prefs instance;

    public static Prefs getInstance() {
        return instance;
    }

    public void saveImage (String uri){
        preferences.edit().putString("saveImage",uri).apply();
    }

    public String getImageUri(){
       return preferences.getString("saveImage",null);
    }
    public void delete(){
        preferences.edit().remove("saveImage").apply();
     }


    public Prefs(Context context) {
        instance = this;
        preferences= context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }

    public void saveBoardState(){
        preferences.edit().putBoolean("isShow",true).apply();
    }
    public boolean isBoardShown(){
        return preferences.getBoolean("isShow",false);
    }
}
