package thrr.asmr.finalproject.com.thrr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class introActivity extends AppCompatActivity {

    Handler h;//핸들러 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //인트로화면이므로 타이틀바를 없앤다
        setContentView(R.layout.activity_intro);

        h= new Handler(); //딜래이를 주기 위해 핸들러 생성
        h.postDelayed(runnable, 2000); // 딜레이

    }

    Runnable runnable = new Runnable(){
        @Override
        public void run(){
            startActivity(new Intent(introActivity.this, LoginActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);  //overridePendingTransition 이란 함수를 이용하여 fade in,out 효과를줌.
        }
    };

    //인트로 중에 뒤로가기를 누를 경우 핸들러를 끊어버려 아무일 없게 만드는 부분
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        h.removeCallbacks(runnable);
    }

}
