package thrr.asmr.finalproject.com.thrr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class choiceActivity extends AppCompatActivity {

    private Button btn_sleep, btn_focus, btn_chart, btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        btn_sleep = (Button) findViewById(R.id.btn_sleep);
        btn_focus = (Button) findViewById(R.id.btn_focus);
        btn_chart = (Button) findViewById(R.id.btn_chart);
        btn_setting = (Button) findViewById(R.id.btn_setting);


        btn_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choiceActivity.this, SleepActivity.class));
            }
        });

        btn_focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choiceActivity.this, FocusActivity.class));
            }
        });

        btn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "아직 미구현이야.", Toast.LENGTH_LONG).show();
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choiceActivity.this, SetActivity.class));
            }
        });
    }
}
