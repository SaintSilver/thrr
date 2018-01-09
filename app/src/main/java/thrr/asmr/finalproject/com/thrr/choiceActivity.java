package thrr.asmr.finalproject.com.thrr;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(choiceActivity.this, new String[]{android.Manifest.permission.RECORD_AUDIO,android.Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }

        btn_sleep = (Button) findViewById(R.id.btn_sleep);
        btn_focus = (Button) findViewById(R.id.btn_focus);
        btn_chart = (Button) findViewById(R.id.btn_chart);
        btn_setting = (Button) findViewById(R.id.btn_setting);

        btn_sleep.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));
        btn_focus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));
        btn_chart.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));
        btn_setting.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));

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
                startActivity(new Intent(choiceActivity.this, MainActivity.class));
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
