package kr.study.chatapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mInputMessage=(EditText) findViewById(R.id.input_message);
        mSendMessage=(Button) findViewById(R.id.send_message);
        mMessageLog=(LinearLayout) findViewById(R.id.messaage_log);
        mCpuMessage=(TextView) findViewById(R.id.cpu_message);
        mSendMessage.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.equals(mSendMessage)) { //Send 버튼 누르면
            String inputText = mInputMessage.getText().toString();
            String answer;
            TextView userMessage = new TextView(this); //새로운 TextView를 만듦
            userMessage.setBackgroundResource(R.drawable.user_message); //메세지 배경색
            userMessage.setText(inputText); //TextView에 입력한 텍스트를 설정
            //말풍선을 메세지 크기에 맞춤
            LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            userMessageLayout.gravity=Gravity.END;

            //cpuMessage에서도 사용하기 위해 final 사용 / 단말에 따라 최적의 간격을 띄움
            final int marginSize=getResources().getDimensionPixelSize(R.dimen.message_margin);
            userMessageLayout.setMargins(0, marginSize, 0, marginSize); //간격 설정

            mMessageLog.addView(userMessage, 0, userMessageLayout); //TextView를 View 맨 위에 설정

            if (inputText.contains("자기소개")) {
                answer = "타카미 치카(高海千歌)! 고등학교 2학년이고 생일은 8월 1일이야.";
            } else if(inputText.contains("안녕")){
                answer = "안녕~ 오늘도 기운차게!";
            } else if(inputText.contains("호노카")){
                answer = "호노카상은 나에게 있어 닿지 않는 별일지도 몰라.. 그렇다고 풀죽어 가만히 있을 수는 없지!";
            } else if(inputText.contains("뮤즈")){
                answer = "소중한 것들은 모두 뮤즈에게 배웠어. 나도 언젠가 꿈을 이루고 싶어!";
            } else if(inputText.contains("여관")){
                answer = "우리집은 여관을 하고 있어. 오래된 집이지만 괜찮다면 놀러와줘~";
            }
              else if (inputText.contains("쓰리사이즈")) {
                answer = "엣..? 그..그건 부끄러운데.. 82..59..83..으아아아~!";
            } else if (inputText.contains("개그")) {
                double random = Math.random() * 5d;
                if (random < 1d) {
                    answer = "인도는 지금 몇 시게? 인도네시아!";
                } else if (random < 2d) {
                    answer = "비가 한 시간동안 내리면? 추적60분!";
                } else if (random < 3d) {
                    answer = "손가락은 영어로 핑거. 주먹은 영어로? 오므린거!";
                } else if (random < 4d) {
                    answer = "고양이를 싫어하는 동물은? '미어'캣!";
                } else {
                    answer = "남자는 역시 힘이지. 여자는? 헐(her)!";
                }
            } else if (inputText.contains("몇 시")) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                answer = String.format("지금 시간은 %1$d시 %2$d분 %3$d초야.", hour, minute, second);
            } else if (inputText.contains("며칠")) {
                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH);
                int date = cal.get(Calendar.DATE);
                answer = String.format("오늘은 %1$d월 %2$d일이야.", month + 1, date);
            } else if (inputText.contains("털")) {
                double random2 = Math.random() * 3d;
                if (random2 < 1d) {
                    answer = "그만 좀 뽑아!";
                } else if (random2 < 2d) {
                    answer = "뽀...뽑지 말아줘...";
                } else {
                    answer = "다스케테 뽑기 방지위원회~!";
                }
            } else if(inputText.contains("에잇")){
                answer=String.format("아얏!");
            }

            else {
                double random3 =Math.random()*3d;
                if(random3<1d){
                 answer="다시 말해줄래?";}
                else if(random3<2d){
                    answer="그 질문은 잘 모르겠어...";
                }else {
                    answer="응?";
                }
            }


            final TextView cpuMessage=new TextView(this); //내부 클래스에서 참조하기 위해 final 선언
            cpuMessage.setBackgroundResource(R.drawable.cpu_message);
            cpuMessage.setText(answer);
            cpuMessage.setGravity(Gravity.START);

            mInputMessage.setText("");
            TranslateAnimation userMessageAnimation = new TranslateAnimation(mMessageLog.getWidth(),0,0,0);
            userMessageAnimation.setDuration(1000);
            userMessageAnimation.setAnimationListener(
                    new Animation.AnimationListener(){ //클래스 이름이 선언되지 않은 내부 클래스를 익명- 이라 함

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    LinearLayout.LayoutParams cpuMessageLayout = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    cpuMessageLayout.gravity=Gravity.START;
                    cpuMessageLayout.setMargins(marginSize,marginSize,marginSize,marginSize);
                    mMessageLog.addView(cpuMessage,0, cpuMessageLayout);
                    TranslateAnimation cpuAnimation = new TranslateAnimation(-mMessageLog.getWidth(),0,0,0);
                    cpuAnimation.setDuration(1000);
                    cpuMessage.setAnimation(cpuAnimation);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            userMessage.startAnimation(userMessageAnimation);


        }
    }
}
