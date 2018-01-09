package thrr.asmr.finalproject.com.thrr;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SleepActivity extends AppCompatActivity implements SensorEventListener {

    private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3, player;
    private ListView lv;
    private TabHost tabHost_1;
    private ImageView iv_focus, iv_chart, iv_setting, question;
    private Button[] btn_array = new Button[50];
    private playListAdapter adapter;
    private TextView textView, textView2;
    private Button btn_lock, btn_time, btn_reset;

    private int[] musicID = {
            R.raw.rain, R.raw.bird, R.raw.bug, R.raw.leaves, R.raw.cicada, R.raw.fire, R.raw.snow, R.raw.valley, R.raw.waterdrops, R.raw.wave,
            R.raw.blanket, R.raw.book, R.raw.chopping, R.raw.cream, R.raw.hairbrushing, R.raw.ice, R.raw.keyboard, R.raw.pencil, R.raw.piano, R.raw.scissors,
            R.raw.egg, R.raw.hairdryer, R.raw.plasticbag, R.raw.pountainpen, R.raw.sand, R.raw.shampoo, R.raw.slime, R.raw.soap, R.raw.train, R.raw.zengarden,
            R.raw.chicken, R.raw.conflakes, R.raw.cracker, R.raw.dango, R.raw.hotdog, R.raw.jelly, R.raw.macaron, R.raw.noodle, R.raw.pizza, R.raw.shrimppuffing,
            R.raw.carving, R.raw.ear, R.raw.earblowing, R.raw.hand, R.raw.heartbeat, R.raw.lids, R.raw.scratch, R.raw.shaving, R.raw.tapping, R.raw.walking
    };

    int[] grayIcon = {R.drawable.rain7,R.drawable.bird7,R.drawable.bug7,R.drawable.leaves7,R.drawable.cicada7,R.drawable.fire7,R.drawable.snow7,R.drawable.valley7,R.drawable.waterdrop7,R.drawable.wave7,
            R.drawable.blanket27,R.drawable.book27,R.drawable.knife27,R.drawable.cream27,R.drawable.hiarbrush27,R.drawable.ice27,R.drawable.keyboard227,R.drawable.pencil7,R.drawable.piano27,R.drawable.scissors7,
            R.drawable.egg7,R.drawable.hairdryer27,R.drawable.plasticbag7,R.drawable.pountainpen7,R.drawable.sand7,R.drawable.shampoo7,R.drawable.slime7,R.drawable.soap7,R.drawable.train7,R.drawable.zengarden27,
            R.drawable.chicken27,R.drawable.conflakes27,R.drawable.cracker27,R.drawable.dango27,R.drawable.hotdog27,R.drawable.jelly27,R.drawable.macaron27,R.drawable.noodle7,R.drawable.pizza7,R.drawable.shrimppuffing7,
            R.drawable.carving27,R.drawable.ear7,R.drawable.earblow7,R.drawable.hand27,R.drawable.heart7,R.drawable.lids7,R.drawable.scratch7,R.drawable.shaving7,R.drawable.tapping7,R.drawable.walking7,
    };

    private int[] whiteIcon = {
            R.drawable.rain125, R.drawable.bird5, R.drawable.noun_308781_cc5, R.drawable.noun_1095263_cc5, R.drawable.cicada5, R.drawable.noun_7380_cc5, R.drawable.winter5, R.drawable.noun_3411_cc5, R.drawable.faucet5, R.drawable.wave5,
            R.drawable.blanket5, R.drawable.book5, R.drawable.knife5, R.drawable.cream5, R.drawable.hiarbrush5, R.drawable.ice5, R.drawable.keyboard5, R.drawable.pen5, R.drawable.piano5, R.drawable.scissors5,
            R.drawable.egg5, R.drawable.hairdryer5, R.drawable.plasticbag5, R.drawable.pountainpen5, R.drawable.sand5, R.drawable.shampoo5, R.drawable.sli5, R.drawable.soap5, R.drawable.train5, R.drawable.zengarden5,
            R.drawable.chicken5, R.drawable.conflakes5, R.drawable.cracker5, R.drawable.dango5, R.drawable.hotdog5, R.drawable.jelly5, R.drawable.macaron5, R.drawable.noodle5, R.drawable.pizza5, R.drawable.shrimppuffing5,
            R.drawable.carving5, R.drawable.ear5, R.drawable.earblow5, R.drawable.hand5, R.drawable.heart5, R.drawable.lid5, R.drawable.scratch5, R.drawable.shaving5, R.drawable.tapping5, R.drawable.walking5
    };

    private ArrayList<playListVO> list = new ArrayList<>();


    //알람
    private AlarmManager mManager;
    private int hour, min = 00;

    // 현재시간
    private String getTime1 = null;   //년
    private String getTime2 = null;   //월
    private String getTime3 = null;   //일
    private String getTime4 = null;   //시
    private String getTime5 = null;   //분

    int alarmHour;          //알람설정시간
    int alarmMin;           //알람설정분

    private TimePickerDialog timePickerDialog = null;   //시간설정창 띄우기
    private SharedPreferences spf = null;               //설정시간 저장

    private NotificationManager mNotification;  //??notification??
    private GregorianCalendar mCalendar;        //그레고리언 달력

    //조도, 센서 관련 객체
    private SensorManager mSensorManager;
    private Sensor mSensor;
    SensorManager m_sensor_manager;
    Sensor m_light_sensor;
    ArrayList<String> meanArray= new ArrayList<String>(); // 조도 값을 담아놓는 ArrayList
    float k = 0; // 평균
    String str;

    // 소리센서 객체
    private AudioReader audioReader;
    private int sampleRate = 8000;
    private int inputBlockSize = 256;
    private int sampleDecimate = 1;
    private Boolean check =false;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        //소리 관련
        audioReader = new AudioReader();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);


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

        //알람부분
        textView = (TextView)findViewById(R.id.tv_clock);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/digital.ttf"));
        btn_time = (Button)findViewById(R.id.btn_time);
        btn_time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));
        btn_lock = (Button)findViewById(R.id.btn_sleepEnd);
        btn_lock.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));
        btn_reset = (Button)findViewById(R.id.btn_sleep_time_save);
        btn_reset.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));
        question = findViewById(R.id.question);

        //로고
        textView2 = findViewById(R.id.textView2);
        textView2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));

        //메뉴버튼 3개
        iv_focus = (ImageView) findViewById(R.id.iv_focus);
        iv_chart = (ImageView) findViewById(R.id.iv_chart);
        iv_setting = (ImageView) findViewById(R.id.iv_setting);

        //조도
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // sensor Service 얻어오기
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT); // 조도 센서랍니다

        //데시벨측정


        //메뉴버튼 3개
        iv_focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i<list.size(); i++){
                    list.get(i).getMediaPlayer().stop();
                    list.get(i).getMediaPlayer().release();
                    list.get(i).setMediaPlayer(null);
                    list.get(i).getButton().setEnabled(true);
                }
                list = new ArrayList<>();
                adapter.notifyDataSetChanged();
                startActivity(new Intent(SleepActivity.this, FocusActivity.class));
                finish();
            }
        });

        iv_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i<list.size(); i++){
                    list.get(i).getMediaPlayer().stop();
                    list.get(i).getMediaPlayer().release();
                    list.get(i).setMediaPlayer(null);
                    list.get(i).getButton().setEnabled(true);
                }
                list = new ArrayList<>();
                adapter.notifyDataSetChanged();
                startActivity(new Intent(SleepActivity.this, PieChartActivity.class));
                finish();
            }
        });

        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<list.size(); i++){
                    list.get(i).getMediaPlayer().stop();
                    list.get(i).getMediaPlayer().release();
                    list.get(i).setMediaPlayer(null);
                    list.get(i).getButton().setEnabled(true);
                }
                list = new ArrayList<>();
                adapter.notifyDataSetChanged();
                startActivity(new Intent(SleepActivity.this, SetActivity.class));
                finish();
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        tabHost_1 = (TabHost) findViewById(R.id.tabHost1);
        tabHost_1.setup();

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"tab1")
        TabHost.TabSpec ts1 = tabHost_1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.tab1);
        ts1.setIndicator("자연");
        tabHost_1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost_1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.tab2);
        ts2.setIndicator("사물");
        tabHost_1.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost_1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.tab3);
        ts3.setIndicator("사물2");
        tabHost_1.addTab(ts3);

        TabHost.TabSpec ts4 = tabHost_1.newTabSpec("Tab Spec 4");
        ts4.setContent(R.id.tab4);
        ts4.setIndicator("이팅");
        tabHost_1.addTab(ts4);

        TabHost.TabSpec ts5 = tabHost_1.newTabSpec("Tab Spec 5");
        ts5.setContent(R.id.tab5);
        ts5.setIndicator("기타");
        tabHost_1.addTab(ts5);

        for(int i=0;i<tabHost_1.getTabWidget().getChildCount();i++) {
            TextView tv = (TextView) tabHost_1.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));
            tv.setTextSize(12);
        }

        /*몰입모드 (하단소프트키, 상태바 숨김) */
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i("Is on?", "Turning immersive mode mode off. ");
        } else {
            Log.i("Is on?", "Turning immersive mode mode on.");
        }
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        /*몰입모드 끝 */


    //listview
        lv = (ListView) findViewById(R.id.musiclistview1);

        for (int i = 0; i < btn_array.length; i++) {
            final int btn_id = getResources().getIdentifier("button_" + (i + 1), "id", "thrr.asmr.finalproject.com.thrr"); //버튼 아이디 한번에.
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
                            btn_array[finalI].setBackgroundResource(grayIcon[finalI]);
                            list.add(new playListVO(whiteIcon[finalI], btn_array[finalI], mediaPlayer1, "sleep",finalI));
                            btn_array[finalI].setEnabled(false);

                            break;
                        case 1: //소리 하나 선택중
                            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer2.setLooping(true);
                            mediaPlayer2.start();
                            btn_array[finalI].setBackgroundResource(grayIcon[finalI]);
                            list.add(new playListVO(whiteIcon[finalI], btn_array[finalI], mediaPlayer2, "sleep",finalI));
                            btn_array[finalI].setEnabled(false);

                            break;
                        case 2: //소리 두개 선택중
                            mediaPlayer3 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer3.setLooping(true);
                            mediaPlayer3.start();
                            btn_array[finalI].setBackgroundResource(grayIcon[finalI]);
                            list.add(new playListVO(whiteIcon[finalI], btn_array[finalI], mediaPlayer3, "sleep",finalI));
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
                Log.v("시간 : " , hour+"");
                Log.v("분 : ", min+"");
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

                /*조도센서로 조도값 평균내기*/
                for(int j=0; j<meanArray.size();j++){
                    k+=Float.parseFloat(meanArray.get(j)); // 조도 값이 담겨있는 ArrayList 안의 값을 모두 더해줌
                }
                k = k/meanArray.size(); //평균내기

                /* ==================K 값을 서버로 전달하면 돼 ===================*/
                Log.v("조도값 :::::::::::::::",String.valueOf(k));

                /*조도센서 끝*/

                /*소리 */
                doStop();
                Log.v("소리측정 횟수 :::::::::::::",String.valueOf(count));

                /* =================count값 서버전달하면 돼 =====================*/

                /*소리 끝*/

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
                //푸시
                new AlarmHATT(getApplicationContext()).Alarm();
                btn_lock.setVisibility(View.INVISIBLE);
                btn_time.setVisibility(View.INVISIBLE);
                btn_reset.setVisibility(View.VISIBLE);

                /*소리측정 시작*/
                doStart();
            }
        });
    }

    //푸시
    public class AlarmHATT {
        private Context context;
        public AlarmHATT(Context context) {
            this.context=context;
        }

        public void Alarm() {
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(SleepActivity.this, BroadcastD.class);

            PendingIntent sender = PendingIntent.getBroadcast(SleepActivity.this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)+1);

            //알람 예약
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }
    }


    //알람의 설정
    private void setAlarm() {
        mManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (hour < Integer.parseInt(getTime4) || (hour == Integer.parseInt(getTime4) && min < Integer.parseInt(getTime5))) { //지금 시간보다 설정시간이 작으면 다음날이 되도록 날짜를 +1
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
    void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("알람시간을 설정할 수 있습니다. 알람을 켜두시면 조도,소음값이 서버에 전달되어 수면환경을 분석할 수 있습니다.");
        builder.setPositiveButton("알겠어요!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    /* 조도센서 관련 메소드 */
    //센서 바뀌면 호출됨
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            str = "밝기 : " + event.values[0] + "lux"; // 조도 센서의 경우 event.values[0]에 조도값이 있다고 한다.
            meanArray.add(String.valueOf(event.values[0])); // float 형인 이벤트 값을 String 형으로
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener( this, mSensor,SensorManager.SENSOR_DELAY_UI); // onCreate에서 생성한 애를 등록함
    }

    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this); //unregister
    }

    /*조도 끝*/

    /*소리측정 관련 메소드*/
    public void doStart()
    {
        audioReader.startReader(sampleRate, inputBlockSize * sampleDecimate, new AudioReader.Listener()
        {
            @Override
            public final void onReadComplete(int dB){ receiveDecibel(dB); }

            @Override
            public void onReadError(int error){ }
        });
    }

    private void receiveDecibel(final int dB)
    {
        Log.e("###", dB+" dB");

        if(dB+40>30){
            check = true;
        }else{
            check = false;
        }
        if(check){
            count ++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doStop(){ audioReader.stopReader(); }
    /*소리측정 끝*/
}