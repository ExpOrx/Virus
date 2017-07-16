package ReceiverPackage;

/**
 * Created by Administrator on 2017/4/25.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ServicePackage.PhoneService;


public class PhoneBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, PhoneService.class);
        context.startService(service);   //启动服务
    }
}