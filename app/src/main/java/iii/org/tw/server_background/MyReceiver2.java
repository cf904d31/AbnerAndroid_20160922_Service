package iii.org.tw.server_background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver2 extends BroadcastReceiver {
    public MyReceiver2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d("Abner","Boot");

        Intent it = new Intent(context, MyService.class);
        context.startService(it);


    }
}
