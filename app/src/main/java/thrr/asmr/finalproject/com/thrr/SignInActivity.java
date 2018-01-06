package thrr.asmr.finalproject.com.thrr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    EditText joinEmail;
    EditText joinPw;
    Button joinBtn;
    Button cancelBtn;

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
                String inputEmail = String.valueOf(joinEmail.getText());
                String inputPw = String.valueOf(joinPw.getText());

                //DB에 올릴거야.

                //DB 완료 후 이전창으로
                finish();
            }
        });

    }
}
