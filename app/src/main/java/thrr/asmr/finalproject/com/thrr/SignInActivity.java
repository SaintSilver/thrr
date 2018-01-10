package thrr.asmr.finalproject.com.thrr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    private EditText joinEmail, joinPw;
    private Button joinBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        joinEmail = (EditText) findViewById(R.id.joinEmail);
        joinPw = (EditText) findViewById(R.id.joinPw);
        joinBtn = (Button) findViewById(R.id.joinBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        //취소버튼
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = String.valueOf(joinEmail.getText()).trim();
                String inputPw = String.valueOf(joinPw.getText()).trim();

                if(inputEmail.equals("") | inputPw.equals("")){
                    Toast.makeText(getApplicationContext(),"빈 칸이 있습니다.",Toast.LENGTH_LONG).show();
                }else{
                    /* =================== DB 회원테이블에 올리는 부분 ======================= */
                    String sendmsg = "vision_write";
                    try{
                        new Task(sendmsg).execute("1",inputEmail, inputPw).get();//보내는것
                        Task task = new Task();
                        String result = task.receiveMsg;
                        Log.v("결과:",result);
                        if(result.equals("false")) {
                            Toast.makeText(getApplicationContext(), "가입 실패..", Toast.LENGTH_LONG).show();
                        }else if(result.equals("true")){
                            Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    /* ================= DB 완료 ====================== */

                }


            }
        });
    }
}
