package kr.rexkim.privatechatting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.net.URLDecoder;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ((action != null) && (action.equals("com.google.android.c2dm.intent.RECEIVE"))) {
            String text = intent.getStringExtra("text");
            String data = "";
            try {
                data = URLDecoder.decode(text, "UTF-8");
            } catch(Exception ex) { ex.printStackTrace(); }

            //명시적intent로 쏴주기
            Intent tmpintent = new Intent(context, MainActivity.class);
            tmpintent.putExtra("text",data);
            tmpintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(tmpintent);
        }
    }
}
