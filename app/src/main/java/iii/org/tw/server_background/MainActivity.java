package iii.org.tw.server_background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private MyReceiver receiver;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("bradmp3");
        registerReceiver(receiver,filter);
    }

    public void test1(View v) {
        Intent it = new Intent(this,MyService.class);
        startService(it);
    }

    public void test2(View v) {
        Intent it = new Intent(this,MyService.class);
        stopService(it);
    }

    public void test3(View v) {

    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int len = intent.getIntExtra("len",-1);
            seekBar.setMax(len);
        }
    }
}
