package com.example.crcakme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView=(TextView) findViewById(R.id.textview);
                Toast.makeText(MainActivity.this,"Please click me",Toast.LENGTH_LONG).show();
                String result = "Cracked Me!";
                textView.setText(result);
            }
        });
    }
}
