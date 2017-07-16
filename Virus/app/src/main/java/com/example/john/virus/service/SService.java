package com.example.john.virus.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by john on 17-4-9.
 */

public class SService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //new MService();
        //onDestroy();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        new MService().onCreate();
        super.onDestroy();
    }
}
