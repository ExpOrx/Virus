package com.example.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 4/30/17.
 */

public class UserDao {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private StringBuffer sql_insert;
    private List<User> users;
    public UserDao(Context context){
        this.dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
        sql_insert = new StringBuffer();
        sql_insert.append("INSERT INTO users(name,gender,age,phoneNumber,address) ");
        sql_insert.append(" VALUES( ?, ?, ?, ?, ?)");
        users = new ArrayList<User>();
        //测试数据
        for(int i = 0;i <200000;i++){
            User user = new User();
            user.setID(i);
            user.setName("name"+i);
            user.setGender(0);
            user.setAge(user.getAge());
            user.setPhoneNumber("13800138000");
            user.setAddress("GuangDong ShenZhen No."+i);
            users.add(user);
        }
    }
    /**
     * 使用SQLiteDatabase 的execSQL方法插入数据
     * @return 返回执行所需要的时间
     */
    public long insertexecSQL()
    {
        long start=System.currentTimeMillis();
        for(User user:users){
            Object[] bindArgs = {user.getName(),user.getGender(),user.getAge(),user.getPhoneNumber(),user.getAddress()};
            db.execSQL(sql_insert.toString(),bindArgs);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
    /**
     * 使用SQLiteStatement的executeInsert方法插入数据
     * @return 返回执行所需要的时间
     */
    public long insertStatement()
    {
        long start = System.currentTimeMillis();
        for(User user:users){
            SQLiteStatement statement= db.compileStatement(sql_insert.toString());
            statement.bindString(1, user.getName());
            statement.bindLong(2, user.getGender());
            statement.bindLong(3, user.getAge());
            statement.bindString(4, user.getPhoneNumber());
            statement.bindString(5, user.getAddress());
            statement.executeInsert();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}