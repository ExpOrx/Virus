package com.example.john.virus.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by john on 18/03/17.
 */

public class MService extends Service {

    private static final String TAG = "LocalService";

    @Override
    public IBinder onBind(Intent intent) {
        Bundle bundle = intent.getExtras();

        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        //new SService();
        //onDestroy();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        onCreate();
        new SService().onCreate();
        super.onDestroy();
    }



}
