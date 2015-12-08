package com.gofriend.vienngan.vngofriend.application;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by tmvien on 12/8/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //enable local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
