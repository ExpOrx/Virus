package ServicePackage;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by John on 2017/4/25.
 */

public class PhoneListenerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate() {

        super.onCreate();

        Log.i("TAG", "服务启动了");

        // 对电话的来电状态进行监听

        TelephonyManager telManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        // 注册一个监听器对电话状态进行监听

        telManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);

    }

    private class MyPhoneStateListener extends PhoneStateListener {

        MediaRecorder recorder;

        File audioFile;

        String phoneNumber;

        public void onCallStateChanged(int state, String incomingNumber) {

            switch (state) {

                case TelephonyManager.CALL_STATE_IDLE: /* 无任何状态时 */

                    if (recorder != null) {

                        recorder.stop();// 停止刻录

                        recorder.reset();// 重设

                        recorder.release();// 刻录完成一定要释放资源

                    }

                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK: /* 接起电话时 */

                    try {

                        recorder = new MediaRecorder();

                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 设置音频采集原

                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);// 内容输出格式

                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // 音频编码方式

                        // recorder.setOutputFile("/sdcard/myvoice.amr");

                        audioFile = new File(Environment.getExternalStorageDirectory(), phoneNumber + "_" + System.currentTimeMillis() + ".3gp");

                        recorder.setOutputFile(audioFile.getAbsolutePath());

                        Log.i("TAG", audioFile.getAbsolutePath());

                        recorder.prepare(); // 预期准备

                        recorder.start();

                    } catch (IllegalStateException e) {

                        e.printStackTrace();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                    break;

                case TelephonyManager.CALL_STATE_RINGING: /* 电话进来时 */

                    phoneNumber = incomingNumber;

                    break;

                default:

                    break;

            }

            super.onCallStateChanged(state, incomingNumber);

        }
    }
}
