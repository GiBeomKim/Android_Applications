package kr.study.debugapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        TextView textView = (TextView) findViewById(R.id.debugtext);
        int result=calc(10);
        textView.setText(String.valueOf(result));
    }

    private int calc(int x){
        int result=0;
        for(int i=1; i<=x; i++){
            result+=i;
            Log.d("result", String.valueOf(i)+":"+String.valueOf(result));
        }
        return result;
    }


}