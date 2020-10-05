package com.alice.afroapp;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class AfroApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

     //   FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
