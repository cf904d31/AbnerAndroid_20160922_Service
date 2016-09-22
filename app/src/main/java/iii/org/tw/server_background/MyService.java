package iii.org.tw.server_background;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private MediaPlayer mp;
    private Timer timer;

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

        timer = new Timer();

        mp = MediaPlayer.create(this,R.raw.littlt_lucky);

        //Log.d("Abner","len" + mp.getDuration());

        Intent it = new Intent("AbnerMp3");
        it.putExtra("len",mp.getDuration());
        sendBroadcast(it);

    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            if (mp != null && mp.isPlaying()) {
                Intent it = new Intent("AbnerMp3");
                it.putExtra("now",mp.getCurrentPosition());
                sendBroadcast(it);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d("Abner","onStartCommand");
        int skip = intent.getIntExtra("skip",-1);

        if (skip == -1) {
            mp.start();
            timer.schedule(new MyTask(),0, 500);
        } else {
            mp.seekTo(skip);
        }


        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        //Log.d("Abner","onDestroy");



        if (mp != null) {
            if(mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            mp = null;
        }
        if (timer != null) {
            timer.purge();
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }
}
