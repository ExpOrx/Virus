package com.example.john.virus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by john on 19/03/17.
 */

public class Login extends Activity {
    public String username ;
    public String password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.loginBtn);
        button.setBackgroundColor(Color.parseColor("#00AFEF"));
        final EditText userName = (EditText) findViewById(R.id.loginId);
        final EditText passWord = (EditText) findViewById(R.id.loginPassword);
        username = userName.getText().toString();
        password =passWord.getText().toString();
        String con = username+" "+password;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"密码或用户名错误",Toast.LENGTH_SHORT).show();
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }


}
