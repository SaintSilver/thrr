package thrr.asmr.finalproject.com.thrr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class choiceActivity extends AppCompatActivity {

    Button btn_sleep, btn_focus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        btn_sleep = (Button) findViewById(R.id.btn_sleep);
        btn_focus = (Button) findViewById(R.id.btn_focus);


        btn_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choiceActivity.this, MainActivity.class));
            }
        });

        btn_focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choiceActivity.this, MainActivity.class));
            }
        });
    }
}
