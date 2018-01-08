package thrr.asmr.finalproject.com.thrr;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class FocusActivity extends AppCompatActivity{

    private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3 ;
    private ListView lv;
    private TabHost tabHost1;
    private ImageView question2, iv_sleep, iv_chart, iv_setting;
    int OVERLAY_PERMISSION_CODE= 1;

    private int[] musicID = {
            R.raw.rain, R.raw.bird, R.raw.bug, R.raw.leaves, R.raw.cicada, R.raw.fire, R.raw.snow, R.raw.valley, R.raw.waterdrops, R.raw.wave,
            R.raw.blanket, R.raw.book, R.raw.chopping, R.raw.cream, R.raw.hairbrushing, R.raw.ice, R.raw.keyboard, R.raw.pencil, R.raw.piano, R.raw.scissors,
            R.raw.egg, R.raw.hairdryer, R.raw.plasticbag, R.raw.pountainpen, R.raw.sand, R.raw.shampoo, R.raw.slime, R.raw.soap, R.raw.train, R.raw.zengarden,
            R.raw.chicken, R.raw.conflakes, R.raw.cracker, R.raw.dango, R.raw.hotdog, R.raw.jelly, R.raw.macaron, R.raw.noodle, R.raw.pizza, R.raw.shrimppuffing,
            R.raw.carving, R.raw.ear, R.raw.earblowing, R.raw.hand, R.raw.heartbeat, R.raw.lids, R.raw.scratch, R.raw.shaving, R.raw.tapping, R.raw.walking};

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
    private Button[] btn_array = new Button[50];
    private playListAdapter adapter;

    //영만
    private int alarmTime = -1;
    private long backKeyPressedTime = 0;
    private int hour, min = 00;
    private WindowManager manager;
    private customViewGroup view;

    private TextView textView = null;

    private Button btn_lock, btn_time ;

    private TimePickerDialog timePickerDialog = null;
    private boolean askedForOverlayPermission;
    // 여기까지 영만

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        addOverlay();

        hour= getSharedPreferences("time", MODE_PRIVATE).getInt("hour", 00);//알람시간 설정의 시
        min= getSharedPreferences("time", MODE_PRIVATE).getInt("min", 00);//알람시간 설정의 분

        textView = (TextView)findViewById(R.id.tv_clock);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/digital.ttf"));
        btn_lock = (Button)findViewById(R.id.btn_lock);
        btn_time = (Button)findViewById(R.id.btn_time);

        tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"tab1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.tab1) ;
        ts1.setIndicator("자연") ;
        tabHost1.addTab(ts1) ;

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.tab2) ;
        ts2.setIndicator("사물") ;
        tabHost1.addTab(ts2) ;

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.tab3) ;
        ts3.setIndicator("사물2") ;
        tabHost1.addTab(ts3) ;

        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tab Spec 4") ;
        ts4.setContent(R.id.tab4) ;
        ts4.setIndicator("이팅") ;
        tabHost1.addTab(ts4);

        TabHost.TabSpec ts5 = tabHost1.newTabSpec("Tab Spec 5") ;
        ts5.setContent(R.id.tab5) ;
        ts5.setIndicator("기타") ;
        tabHost1.addTab(ts5) ;

        for(int i=0;i<tabHost1.getTabWidget().getChildCount();i++) {
            TextView tv = (TextView) tabHost1.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#282828"));
            tv.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/letter.ttf"));
            tv.setTextSize(12);
        }

        //listview
        lv = (ListView) findViewById(R.id.musiclistview1);

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

        //다이얼로그
        question2 = (ImageView) findViewById(R.id.question2);
        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        //다이얼로그 끝

        //메뉴버튼 3개
        iv_sleep = (ImageView) findViewById(R.id.iv_sleep);
        iv_chart = (ImageView) findViewById(R.id.iv_chart);
        iv_setting = (ImageView) findViewById(R.id.iv_setting);

        //메뉴버튼 3개
        iv_sleep.setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(FocusActivity.this, SleepActivity.class));
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
                startActivity(new Intent(FocusActivity.this, PieChartActivity.class));
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
                startActivity(new Intent(FocusActivity.this, SetActivity.class));
                finish();
            }
        });

        for(int i = 0 ; i < btn_array.length; i++){
            final int btn_id = getResources().getIdentifier("button_"+(i+1), "id", "thrr.asmr.finalproject.com.thrr"); //버튼 아이디 한번에.
            btn_array[i] = findViewById(btn_id);

            final int finalI = i;
            btn_array[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(list.size()){
                        case 0: //아직 선택한 소리가 없다
                            mediaPlayer1 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer1.setLooping(true);
                            mediaPlayer1.start();
                            btn_array[finalI].setBackgroundResource(whiteIcon[finalI]);
                            list.add(new playListVO(grayIcon[finalI], btn_array[finalI], mediaPlayer1, "focus", finalI));
                            btn_array[finalI].setEnabled(false);
                            break;
                        case 1: //소리 하나 선택중
                            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer2.setLooping(true);
                            mediaPlayer2.start();
                            btn_array[finalI].setBackgroundResource(whiteIcon[finalI]);
                            list.add(new playListVO(grayIcon[finalI], btn_array[finalI], mediaPlayer2, "focus", finalI));
                            btn_array[finalI].setEnabled(false);
                            break;
                        case 2: //소리 두개 선택중
                            mediaPlayer3 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer3.setLooping(true);
                            mediaPlayer3.start();
                            btn_array[finalI].setBackgroundResource(whiteIcon[finalI]);
                            list.add(new playListVO(grayIcon[finalI], btn_array[finalI], mediaPlayer3,"focus", finalI));
                            btn_array[finalI].setEnabled(false);
                            break;
                        case 3: //리스트가 꽉 참
                            Toast.makeText(getApplicationContext(),"3곡까지만 조합할 수 있습니다.", Toast.LENGTH_LONG).show();
                            break;
                    }
                    adapter.notifyDataSetChanged();
                }
            });


            adapter = new playListAdapter(getApplicationContext(), R.layout.musiclist_layout, list);
            ((ListView) findViewById(R.id.musiclistview1)).setAdapter(adapter);
        }
        //시간 설정
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        //시간설정 창 띄우기
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                //hour = timePicker.getHour(); // == i
                //min = timePicker.getMinute(); // == i1
                hour = i;
                min = i1;
                if(hour<10) {
                    if(min<10){
                        textView.setText("0"+hour + " : 0" + min);
                    }else{
                        textView.setText("0"+hour + " : " + min);
                    }

                }else{
                    if(min<10){
                        textView.setText(hour + " : 0" + min);
                    }else{
                        textView.setText(hour + " : " + min);
                    }

                }
                alarmTime = hour*60 + min;
            }
        }, hour, min, true);
        //잠글때 스크롤 안내려오게하고 시간설정 과 시작버튼 사라지게하기
        btn_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scrollLock();

                new Thread(new timeThread()).start();
                if(hour>0||min>0) {
                    btn_lock.setVisibility(View.INVISIBLE);
                    btn_time.setVisibility(View.INVISIBLE);
                }

            }

        });
    }
    @Override
    public void onBackPressed(){
        if(alarmTime <0) {
            for(int i = 0; i<list.size(); i++){
                list.get(i).getMediaPlayer().stop();
                list.get(i).getMediaPlayer().release();
                list.get(i).setMediaPlayer(null);
                list.get(i).getButton().setEnabled(true);
            }
            list = new ArrayList<>();
            adapter.notifyDataSetChanged();
            //알람시간이 0보다 작으면 백키가 눌림

            backButtonFunction();

        }
    }
    //백키 동작 메소드
    public void backButtonFunction(){

        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        //Toast.makeText(getApplicationContext(), ""+backStackCount, Toast.LENGTH_SHORT).show();
        //currentTimeMillis 현재시간이 버튼을 눌린 시간 + 2초 보다 흘럿다면 2초내 클릭 안한것임.
        if (backStackCount > 0) {
            //  FragmentUtil.goBack();
            super.onBackPressed();
        }
        if (backStackCount == 1) return;

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {

            //backKeyPressedTime 버튼을 누른 시간을 입력
            backKeyPressedTime = System.currentTimeMillis();

            super.onBackPressed();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            //    finish();
            //     toast.cancel();
        }

    }
    //핸들러 시간초 변경
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            textView.setText(msg.arg1 + "");
            if(msg.arg1%60<10){
                if (msg.arg1 >= 60) {
                    if(msg.arg1/60<10) {
                        textView.setText("0"+msg.arg1 / 60 + " : 0" + msg.arg1 % 60);
                    }else{
                        textView.setText(msg.arg1 / 60 + " : 0" + msg.arg1 % 60);
                    }
                } else if (msg.arg1 < 60) {
                    textView.setText("00 : 0" + msg.arg1 % 60);
                }
            }else {
                if (msg.arg1 >= 60) {
                    if(msg.arg1/60<10) {
                        textView.setText("0"+msg.arg1 / 60 + " : " + msg.arg1 % 60);
                    }else{
                        textView.setText(msg.arg1 / 60 + " : " + msg.arg1 % 60);
                    }

                } else if (msg.arg1 < 60) {
                    textView.setText("00 : " + msg.arg1 % 60);
                }
            }
            msg.arg1--;
            alarmTime = msg.arg1;
            if(alarmTime<0) {

                btn_lock.setVisibility(View.VISIBLE);
                btn_time.setVisibility(View.VISIBLE);
                scrollOn();

            }
            alarmTime = msg.arg1;
            SharedPreferences sp = getSharedPreferences("time", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putInt("hour", alarmTime/60);
            edit.putInt("min", alarmTime%60);
            edit.commit();
        }
    };

    //쓰레드
    public class timeThread implements Runnable {
        int MAXTIME = alarmTime;

        @Override
        public void run() {

            for (int i = MAXTIME; i >= 0; i--) {

                Message msg = new Message();
                msg.arg1 = i;
                handler.sendMessage(msg);

                try {
                    Thread.sleep(60000); //60초
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //스크롤바 밑으로 pull down 안되게 해주고 되게해주는 메소드들
    public class customViewGroup extends ViewGroup {
        public customViewGroup(Context context) {
            super(context);
        }
        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
        }
        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            Log.v("customViewGroup", "**********Intercepted");
            return true;
        }

    }
    public void scrollLock(){
        manager = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE));
        WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        localLayoutParams.gravity = Gravity.TOP;
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
// this is to enable the notification to recieve touch events
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
// Draws over status bar
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        localLayoutParams.height = (int) (50 * getResources().getDisplayMetrics().scaledDensity);
        localLayoutParams.format = PixelFormat.TRANSPARENT;
        view = new customViewGroup(this);
        manager.addView(view, localLayoutParams);
    }
    public void scrollOn(){
        manager.removeViewImmediate(view);
    }

    public void addOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                askedForOverlayPermission = true;
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_CODE);
            }
        }
    }
    //다이얼로그 메소드
    void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("몰입시간을 설정할 수 있습니다. 홈키를 제외한 버튼은 무력화되며 소음값이 서버에 전달되어 집중환경을 분석할 수 있습니다.");
        builder.setPositiveButton("알겠어요!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    //푸시
    public class AlarmHATT {
        private Context context;
        public AlarmHATT(Context context) {
            this.context=context;
        }

        public void Alarm() {
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(FocusActivity.this, BroadcastF.class);

            PendingIntent sender = PendingIntent.getBroadcast(FocusActivity.this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)+1);

            //알람 예약
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }
    }
}