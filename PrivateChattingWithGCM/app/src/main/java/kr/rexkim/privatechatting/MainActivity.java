package kr.rexkim.privatechatting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GoogleApiClient mGoogleApiClient;
    GoogleCloudMessaging gcm;
    Sender sender;
    Handler handler = new Handler();
    ArrayList<String> clientList = new ArrayList<String>();

    EditText edittext1;
    EditText edittext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext1 = (EditText)findViewById(R.id.editText1);
        edittext2 = (EditText)findViewById(R.id.editText2);

        sender = new Sender(Constants.API_KEY);

        Intent intent = getIntent();
        if(intent != null) intentHandler(intent);
    }
    public void pressButton1(View v) {
        new RegisterTask().execute(null, null, null);
    }
    class RegisterTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            edittext1.setText("Client App registering...");
        }
        @Override
        protected String doInBackground(Void... params) {
            String str;
            try {
                if (gcm == null) gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                Constants.REG_ID = gcm.register(Constants.PROJECT_NUM);
                str = "Device registered, registration ID is\n" + Constants.REG_ID;
            } catch (IOException e) {
                str = "Error in registration:" + e.getMessage();
            }
            return str;
        }
        @Override
        protected void onPostExecute(String msg) { edittext1.setText(msg); }
    }
    public void pressButton2(View v) {
        clientList.clear();
        clientList.add(Constants.REG_ID);
        new SendMsgTask().execute(null, null, null);
    }
    class SendMsgTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() { }

        @Override
        protected String doInBackground(Void... params) {
            String str;
            String text = edittext2.getText().toString();
            try {
                Message.Builder msgbuilder = new Message.Builder();
                Random random = new Random(System.currentTimeMillis());
                String messageCollapseKey = String.valueOf(Math.abs(random.nextInt()));
                msgbuilder.collapseKey(messageCollapseKey).delayWhileIdle(true).timeToLive(60);
                msgbuilder.addData("text", URLEncoder.encode(text, "UTF-8"));
                Message msg = msgbuilder.build();
                MulticastResult result = sender.send(msg, clientList, 2);  // 2 is num.retry
                str = result.getMulticastId()+ "," + result.getRetryMulticastIds() + "," + result.getSuccess();
            } catch (IOException e) {
                str = "Error in sending message" + e.getMessage();
            }
            return str;
        }
        @Override
        protected void onPostExecute(String msg) { }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        intentHandler(intent);
        super.onNewIntent(intent);
    }

    public void intentHandler(Intent intent) {
        String text = intent.getStringExtra("text");
        edittext1.setText("Received message : "+text);
    }

    ///////////////////////////////////////////////////////
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
    }
    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

}
