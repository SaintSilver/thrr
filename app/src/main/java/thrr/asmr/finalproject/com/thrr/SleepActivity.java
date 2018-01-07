package thrr.asmr.finalproject.com.thrr;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class SleepActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3;
    private ListView lv;
    private TabHost tabHost_1;

    private int[] musicID = {
            R.raw.rain, R.raw.bird, R.raw.bug, R.raw.leaves, R.raw.cicada, R.raw.fire, R.raw.snow, R.raw.valley, R.raw.waterdrops, R.raw.wave,
            R.raw.blanket, R.raw.book, R.raw.chopping, R.raw.cream, R.raw.hairbrushing, R.raw.ice, R.raw.keyboard, R.raw.pencil, R.raw.piano, R.raw.scissors,
            R.raw.egg, R.raw.hairdryer, R.raw.plasticbag, R.raw.pountainpen, R.raw.sand, R.raw.shampoo, R.raw.slime, R.raw.soap, R.raw.train, R.raw.zengarden,
            R.raw.chicken, R.raw.conflakes, R.raw.cracker, R.raw.dango, R.raw.hotdog, R.raw.jelly, R.raw.macaron, R.raw.noodle, R.raw.pizza, R.raw.shrimppuffing,
            R.raw.carving, R.raw.ear, R.raw.earblowing, R.raw.hand, R.raw.heartbeat, R.raw.lids, R.raw.scratch, R.raw.shaving, R.raw.tapping, R.raw.walking};

    private ArrayList<playListVO> list = new ArrayList<>();
    private Button[] btn_array = new Button[50];
    private playListAdapter adapter;

    //알람 및 간
    private AlarmManager mManager;
    private int hour = 00;
    private int min = 00;

    TextView textView;

    Button btn_lock, btn_time, btn_reset;

    // 현재시간
    String getTime1 = null;   //년
    String getTime2 = null;   //월
    String getTime3 = null;   //일
    String getTime4 = null;   //시
    String getTime5 = null;   //분

    int alarmHour;          //알람설정시간
    int alarmMin;           //알람설정분

    MediaPlayer player;
    TimePickerDialog timePickerDialog = null;   //시간설정창 띄우기
    SharedPreferences spf = null;               //설정시간 저장

    private NotificationManager mNotification;  //??notification??
    private GregorianCalendar mCalendar;        //그레고리언 달력


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);    //서비스 등록같음
        mCalendar = new GregorianCalendar();                                            //그레고리언 생성

        long now = System.currentTimeMillis();                                          //현재시간 밀리로 가져오기

        Date date = new Date(now);                                                      //현재 밀리타임을 날짜로 변환

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");                   //년
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");                     //월
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd");                     //일
        SimpleDateFormat sdf4 = new SimpleDateFormat("HH");                     //시
        SimpleDateFormat sdf5 = new SimpleDateFormat("mm");                     //분

        getTime1 = sdf1.format(date);                                                   //년월일시분 따로 저장
        getTime2 = sdf2.format(date);
        getTime3 = sdf3.format(date);
        getTime4 = sdf4.format(date);
        getTime5 = sdf5.format(date);
        spf = getSharedPreferences("AlarmTimeSet", MODE_PRIVATE);
        alarmHour = spf.getInt("AlarmHour", -1);                                     //시간 저장한값 가져오고 없으면 -1
        alarmMin = spf.getInt("AlarmMin", -1);                                      //분 저장한값 가져오고 없으면 -1
        player = MediaPlayer.create(getApplicationContext(), R.raw.alarm);                  //알람 소리 플레이어 생성
        if (alarmHour == Integer.parseInt(getTime4) && alarmMin == Integer.parseInt(getTime5)) {  //설정시간과 현재 시간이 같으면 알람 울림

            player.start();
        }

        mManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);               //알람서비스에 등록

        textView = (TextView)findViewById(R.id.textView4);
        btn_lock = (Button)findViewById(R.id.button2);
        btn_time = (Button)findViewById(R.id.button);
        btn_reset = (Button)findViewById(R.id.button3);

        tabHost_1 = (TabHost) findViewById(R.id.tabHost_1);
        tabHost_1.setup();

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"tab1")
        TabHost.TabSpec ts1 = tabHost_1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.tab_1);
        ts1.setIndicator("자연");
        tabHost_1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost_1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.tab_2);
        ts2.setIndicator("사물");
        tabHost_1.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost_1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.tab_3);
        ts3.setIndicator("사물2");
        tabHost_1.addTab(ts3);

        TabHost.TabSpec ts4 = tabHost_1.newTabSpec("Tab Spec 4");
        ts4.setContent(R.id.tab_4);
        ts4.setIndicator("이팅");
        tabHost_1.addTab(ts4);

        TabHost.TabSpec ts5 = tabHost_1.newTabSpec("Tab Spec 5");
        ts5.setContent(R.id.tab_5);
        ts5.setIndicator("기타");
        tabHost_1.addTab(ts5);

        //listview
        lv = (ListView) findViewById(R.id.musiclistview1);

        for (int i = 0; i < btn_array.length; i++) {
            final int btn_id = getResources().getIdentifier("Button_" + (i + 1), "id", "thrr.asmr.finalproject.com.thrr"); //버튼 아이디 한번에.
            btn_array[i] = findViewById(btn_id);

            final int finalI = i;
            btn_array[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (list.size()) {
                        case 0: //아직 선택한 소리가 없다
                            mediaPlayer1 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer1.setLooping(true);
                            mediaPlayer1.start();
                            list.add(new playListVO(R.drawable.day, btn_array[finalI], mediaPlayer1));
                            btn_array[finalI].setEnabled(false);
                            break;
                        case 1: //소리 하나 선택중
                            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer2.setLooping(true);
                            mediaPlayer2.start();
                            list.add(new playListVO(R.drawable.night, btn_array[finalI], mediaPlayer2));
                            btn_array[finalI].setEnabled(false);
                            break;
                        case 2: //소리 두개 선택중
                            mediaPlayer3 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer3.setLooping(true);
                            mediaPlayer3.start();
                            list.add(new playListVO(R.drawable.test, btn_array[finalI], mediaPlayer3));
                            btn_array[finalI].setEnabled(false);
                            break;
                        case 3: //리스트가 꽉 참
                            Toast.makeText(getApplicationContext(), "3곡까지만 조합할 수 있습니다.", Toast.LENGTH_LONG).show();
                            break;
                    }
                    adapter.notifyDataSetChanged();
                }
            });


            adapter = new playListAdapter(getApplicationContext(), R.layout.musiclist_layout, list);
            ((ListView) findViewById(R.id.musiclistview1)).setAdapter(adapter);
        }

        if (alarmHour != -1) {                               //알람설정시간이 설정되어있지않으면 시간설정과 알람설정을 보이게 반대면 안보이게하고 알람끄는것만 보이게
            btn_lock.setVisibility(View.INVISIBLE);
            btn_time.setVisibility(View.INVISIBLE);
            btn_reset.setVisibility(View.VISIBLE);
            textView.setText(alarmHour + " : " + alarmMin);
        } else {
            btn_lock.setVisibility(View.VISIBLE);
            btn_time.setVisibility(View.VISIBLE);
            btn_reset.setVisibility(View.INVISIBLE);
            textView.setText(getTime4 + " : " + getTime5);
        }


        //시간설정 창 띄우기
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {    //시간설정창 띄우기
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                //hour = timePicker.getHour(); // == i
                //min = timePicker.getMinute(); // == i1
                hour = i;
                min = i1;
                if (hour < 10) {
                    if (min < 10) {
                        textView.setText("0" + hour + " : 0" + min);
                    } else {
                        textView.setText("0" + hour + " : " + min);
                    }

                } else {
                    if (min < 10) {
                        textView.setText(hour + " : 0" + min);
                    } else {
                        textView.setText(hour + " : " + min);
                    }

                }

            }
        }, Integer.parseInt(getTime4), Integer.parseInt(getTime5), true);

        btn_reset.setOnClickListener(new View.OnClickListener() {       //알람설정 없애고 노래 중지
            @Override
            public void onClick(View view) {
                resetAlarm();
                if (player.isPlaying()) {
                    player.stop();
                    player.release();
                }
                btn_lock.setVisibility(View.VISIBLE);
                btn_time.setVisibility(View.VISIBLE);
                btn_reset.setVisibility(View.INVISIBLE);
                spf.edit().putInt("AlarmHour", -1).commit();     //spf의 설정을 다시 -1로해서 버튼들이 기본으로 돌아가게 만들어줌
                spf.edit().putInt("AlarmMin", -1).commit();
            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {    //시간 설정클릭시 타임피커 호출
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btn_lock.setOnClickListener(new View.OnClickListener() {    //알람설정시 버튼 안보이게 바꿔줌
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "알림이 설정되었습니다.", Toast.LENGTH_LONG).show();
                setAlarm();
                btn_lock.setVisibility(View.INVISIBLE);
                btn_time.setVisibility(View.INVISIBLE);
                btn_reset.setVisibility(View.VISIBLE);

            }
        });

    }

    //알람의 설정
    private void setAlarm() {
        mManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (hour > Integer.parseInt(getTime4) || (hour == Integer.parseInt(getTime4) && min < Integer.parseInt(getTime5))) { //지금 시간보다 설정시간이 작으면 다음날이 되도록 날짜를 +1
            mCalendar.set(Integer.parseInt(getTime1), Integer.parseInt(getTime2) - 1, Integer.parseInt(getTime3) + 1, hour, min, 00);
            mManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntent());

            spf.edit().putInt("AlarmHour", hour).commit();
            spf.edit().putInt("AlarmMin", min).commit();
        } else {
            mCalendar.set(Integer.parseInt(getTime1), Integer.parseInt(getTime2) - 1, Integer.parseInt(getTime3), hour, min, 00);
            mManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntent());

            spf.edit().putInt("AlarmHour", hour).commit();
            spf.edit().putInt("AlarmMin", min).commit();
        }

    }

    //알람의 해제
    private void resetAlarm() {
        mManager.cancel(pendingIntent());
    }
    //알람의 설정 시각에 발생하는 인텐트 작성
    private PendingIntent pendingIntent() {
        Intent i = new Intent(getApplicationContext(), SleepActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        return pi;
    }
    //시각 설정 클래스의 상태변화 리스너
    public void onTimeChanged (TimePicker view, int hourOfDay, int minute) {
        mCalendar.set (Integer.parseInt(getTime1), Integer.parseInt(getTime2),Integer.parseInt(getTime3), hourOfDay, minute,00);
        Log.i("HelloAlarmActivity",mCalendar.getTime().toString());
    }

    //일자 설정 클래스의 상태변화 리스너
    public void onDateChanged (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mCalendar.set (Integer.parseInt(getTime1), Integer.parseInt(getTime2),Integer.parseInt(getTime3),Integer.parseInt(getTime4),Integer.parseInt(getTime5),00);
        Log.i("HelloAlarmActivity", mCalendar.getTime().toString());
    }

    //뒤로가기 하면 초기화
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        for(int i = 0; i<list.size(); i++){
            list.get(i).getMediaPlayer().stop();
            list.get(i).getMediaPlayer().release();
            list.get(i).setMediaPlayer(null);
            list.get(i).getButton().setEnabled(true);
        }
        list = new ArrayList<>();
        adapter.notifyDataSetChanged();
    }
}
