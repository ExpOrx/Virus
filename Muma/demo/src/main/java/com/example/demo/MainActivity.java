package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends Activity  {

    //寻找控件
    Button btn_add,btn_delete,btn_update,btn_select;
    //创建数据库操作类的实例
    UserDao pd=null;
    List<User> users=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final UserDao dao = new UserDao(this);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_select = (Button) findViewById(R.id.btn_select);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_add.setText(String.valueOf(dao.insertexecSQL()));
                btn_add.setTextSize(35);
            }
        });
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_select.setText(String.valueOf(dao.insertStatement()));
                btn_select.setTextSize(35);
            }
        });

    }


}