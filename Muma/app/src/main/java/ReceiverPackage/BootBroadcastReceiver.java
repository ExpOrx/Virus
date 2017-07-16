package ReceiverPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.john.muma.MainActivity;
import ServicePackage.PhoneListenerService;

/**
 * Created by john on 4/25/17.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final String ACTION = "android.intent.action.BOOT_COMPLETED";
        if (intent.getAction().equals(ACTION)){
            Intent sayHelloIntent = new Intent(context,MainActivity.class);
            sayHelloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(sayHelloIntent);
            Intent serviceIntent = new Intent(context, PhoneListenerService.class);
            context.startService(serviceIntent);
        }
    }

}