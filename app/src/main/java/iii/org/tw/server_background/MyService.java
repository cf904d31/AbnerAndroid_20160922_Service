package iii.org.tw.server_background;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private MediaPlayer mp;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //Log.d("Abner","onCreate");
        super.onCreate();

        mp = MediaPlayer.create(this,R.raw.littlt_lucky);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d("Abner","onStartCommand");
        mp.start();
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        //Log.d("Abner","onDestroy");

        super.onDestroy();

        if (mp != null) {
            if(mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            mp = null;
        }
    }
}
