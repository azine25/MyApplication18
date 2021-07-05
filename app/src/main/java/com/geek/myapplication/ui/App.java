package com.geek.myapplication.ui;

import android.app.Application;

import com.geek.myapplication.Prefs;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new Prefs(this);
    }
}
