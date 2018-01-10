package thrr.asmr.finalproject.com.thrr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private LineChart chart_single1, chart_single2, chart_single3, chart_single4;
    private BarChart day_chart1, day_chart2, day_chart3, day_chart4;

    private TextView textView_date, tv_recommned_1, tv_recommend_2, tv_recommend_3;
    private ImageView iv_recommend_1, iv_recommend_2, iv_recommend_3, iv_under1, iv_under2, iv_under3;
    private Button chartbtn_1, chartbtn_2, chartbtn_3, chartbtn_4, chartbtn_5, chartbtn_6, chartbtn_7, chartbtn_8;
    private SharedPreferences spf;

    String user_id = null;

    Handler handler= null;
    ImageView imageView_m = null;
    ImageView imageView_p = null;
    ArrayList<SleepDTO> sleep_list = new ArrayList<SleepDTO>();
    ArrayList<FocusDTO> focus_list =new ArrayList<FocusDTO>();

    String email = null;
    String dateTime =null;
    String focus_time=null;
    String select_asmr = null;
    String db = null;
    String sleep_time = null;
    String wakeup_time = null;
    String light = null;

    long mNow;
    Date mDate;
    long mNow2;
    Date mDate2;
    long mNow3;
    Date mDate3;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");      // 가져오고 싶은 형식 지정.
    SimpleDateFormat mFormat2 = new SimpleDateFormat("yyyy-MM-dd");     // 가져오고 싶은 형식 지정.
    SimpleDateFormat mFormat3 = new SimpleDateFormat("yyyy-MM-dd");     // 가져오고 싶은 형식 지정.

    MusicListVO musicListVO = new MusicListVO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Json받기
        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        String response = "-1";
        spf = getSharedPreferences("emailspf", MODE_PRIVATE);
        email = spf.getString("email", "");


        try {
            new HttpUtil().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        try {
            new HttpUtil2().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        try {
            new HttpUtil30().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        try {
            new HttpUtil302().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        imageView_m = (ImageView) findViewById(R.id.imageView_m);
        imageView_p = (ImageView) findViewById(R.id.imageView_p);

        tv_recommned_1 = findViewById(R.id.tv_recommend_1);
        tv_recommned_1.setText(musicListVO.getMusicName()[44]);
        tv_recommend_2 = findViewById(R.id.tv_recommend_2);
        tv_recommend_2.setText(musicListVO.getMusicName()[3]);
        tv_recommend_3 = findViewById(R.id.tv_recommend_3);
        tv_recommend_3.setText(musicListVO.getMusicName()[15]);
        iv_recommend_1 = findViewById(R.id.iv_recommend_1);
        iv_recommend_1.setImageResource(musicListVO.getGrayIcon()[44]);
        iv_recommend_2 = findViewById(R.id.iv_recommend_2);
        iv_recommend_2.setImageResource(musicListVO.getGrayIcon()[3]);
        iv_recommend_3 = findViewById(R.id.iv_recommend_3);
        iv_recommend_3.setImageResource(musicListVO.getGrayIcon()[15]);

        iv_under1 = findViewById(R.id.iv_under_1);
        iv_under2 = findViewById(R.id.iv_under_2);
        iv_under3 = findViewById(R.id.iv_under_3);

        iv_under1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FocusActivity.class));
                finish();
            }
        });

        iv_under2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SleepActivity.class));
                finish();
            }
        });

        iv_under3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetActivity.class));
                finish();
            }
        });

        /* === 차트창에 접속하면 DB에서 email에 해당하는 회원 데이터 싹 긁어모아와서 setData하셈. === */
        spf = getSharedPreferences("emailspf", MODE_PRIVATE);
        email = spf.getString("email", ""); // 이메일


        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }




        TabHost tabHost1 = (TabHost) findViewById(R.id.tab_tab);
        tabHost1.setup();

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.tab1);
        ts1.setIndicator("수면모드");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.tab2);
        ts2.setIndicator("집중모드");
        tabHost1.addTab(ts2);

        textView_date = (TextView) findViewById(R.id.textView_date);
        imageView_m = (ImageView) findViewById(R.id.imageView_m);
        imageView_m = (ImageView) findViewById(R.id.imageView_p);

        setData(7, 12);
        setData2(7, 12);
        setData3(7, 12);
        setData4(7, 12);
        setupDayChart();
        setupDayChart2();
        setupDayChart3();
        setupDayChart4();

        if (chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
        if (chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
        if (chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
        if (chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
        if (day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
        if (day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
        if (day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
        if (day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

        imageView_m.setVisibility(View.INVISIBLE);
        imageView_p.setVisibility(View.INVISIBLE);

        findViewById(R.id.chartbtn_1).setOnClickListener(new View.OnClickListener() {       // 기상 시간 (일주일)
            @Override
            public void onClick(View v) {

                setData(7, 12);
                chart_single1.animateX(1000);

                if (chart_single1 != null) chart_single1.setVisibility(View.VISIBLE);
                if (chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if (chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if (chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if (day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if (day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if (day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if (day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_2).setOnClickListener(new View.OnClickListener() {       // 기상 시간 (한달)
            @Override
            public void onClick(View v) {

                setData2(7, 12);
                chart_single2.animateX(1000);
                chart_single2.setNoDataText("");

                if (chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if (chart_single2 != null) chart_single2.setVisibility(View.VISIBLE);
                if (chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if (chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if (day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if (day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if (day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if (day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.chartbtn_3).setOnClickListener(new View.OnClickListener() {       //  취침 시간 (일주일)
            @Override
            public void onClick(View v) {

                setData3(7, 12);
                chart_single2.animateX(1000);
                chart_single2.setNoDataText("");


                if (chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if (chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if (chart_single3 != null) chart_single3.setVisibility(View.VISIBLE);
                if (chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if (day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if (day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if (day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if (day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.chartbtn_4).setOnClickListener(new View.OnClickListener() {       //  취침 시간 (한달)
            @Override
            public void onClick(View v) {

                setData4(7, 12);
                chart_single2.animateX(1000);
                chart_single2.setNoDataText("");


                if (chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if (chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if (chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if (chart_single4 != null) chart_single4.setVisibility(View.VISIBLE);
                if (day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if (day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if (day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if (day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.chartbtn_5).setOnClickListener(new View.OnClickListener() {       // 크기, 밝기, 수면량
            @Override
            public void onClick(View v) {

                setupDayChart3();

                if (chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if (chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if (chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if (chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if (day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if (day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if (day_chart3 != null) day_chart3.setVisibility(View.VISIBLE);
                if (day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_6).setOnClickListener(new View.OnClickListener() {       // 집중 (일주일)
            @Override
            public void onClick(View v) {

                setupDayChart();

                if (chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if (chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if (chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if (chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if (day_chart1 != null) day_chart1.setVisibility(View.VISIBLE);
                if (day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if (day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if (day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_7).setOnClickListener(new View.OnClickListener() {       // 집중 (한달)
            @Override
            public void onClick(View v) {

                setupDayChart2();

                if (chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if (chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if (chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if (chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if (day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if (day_chart2 != null) day_chart2.setVisibility(View.VISIBLE);
                if (day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if (day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_8).setOnClickListener(new View.OnClickListener() {       // 크기, 밝기, 날짜별 집중시간
            @Override
            public void onClick(View v) {

                setupDayChart4();

                if (chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if (chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if (chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if (chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if (day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if (day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if (day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if (day_chart4 != null) day_chart4.setVisibility(View.VISIBLE);
            }
        });
    } //onCreate 메소드 끝

    private void setupDayChart() {      // Bar chart
        day_chart1 = (BarChart) findViewById(R.id.day_chart1);
        day_chart1.setNoDataText("");
        day_chart1.setDrawBarShadow(false);
        day_chart1.setDrawValueAboveBar(true);
        day_chart1.setMaxVisibleValueCount(50);
        day_chart1.setPinchZoom(false);
        day_chart1.setDrawGridBackground(true);

        try {
            new HttpUtil().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Log.v("결과",sleep_list.size()+"");
        ArrayList<Entry> yVals1 = new ArrayList<>();
        String[] temp = new String[3];
        String[] temp1 = new String[3];
        Log.v("결과1",temp1.length+"");
        ArrayList<Float> float_dt = new ArrayList<Float>();
        ArrayList<Float> float_st = new ArrayList<Float>();
        Log.v("결과1", sleep_list.size()+"");
        for (int i = 0; i <  sleep_list.size(); i++) {
            temp = sleep_list.get(i).getDatetime().split("-");

            for(int j = 0; j <  temp.length; j+=3){
                float_dt.add(Float.parseFloat(temp[j]+temp[j+1]+temp[j+1]));
            }
        }
        Log.v("결과1",temp.length+"");


        for (int i = 0; i <  sleep_list.size(); i++) {

            float_st.add(Float.parseFloat(sleep_list.get(i).getSleep_amount()));

        }
        ArrayList<BarEntry> year_barEntries = new ArrayList<>();
        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries.add(new BarEntry(i-7, float_st.get(i).floatValue()));
        }


        ArrayList<BarEntry> year_barEntries2 = new ArrayList<>();
        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries2.add(new BarEntry(i-7, Float.parseFloat(sleep_list.get(i).getLight()+100+"")));
        }

        ArrayList<BarEntry> year_barEntries3 = new ArrayList<>();

        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries2.add(new BarEntry(i-7, Float.parseFloat(sleep_list.get(i).getDb()+50+"")));
        }

        BarDataSet year_barDataSet = new BarDataSet(year_barEntries, "수면량");
        year_barDataSet.setColor(Color.parseColor("#81E4FD"));

        BarDataSet year_barDataSet2 = new BarDataSet(year_barEntries2, "조도");
        year_barDataSet2.setColor(Color.parseColor("#FDA5A7"));

        BarDataSet year_barDataSet3 = new BarDataSet(year_barEntries3, "소리");
        year_barDataSet3.setColor(Color.parseColor("#FDA5A7"));

        BarData year_data = new BarData(year_barDataSet, year_barDataSet2, year_barDataSet3);

        float groupspace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        ArrayList<String> year_name = new ArrayList<>();

        year_name.add("-7");
        year_name.add("-6");
        year_name.add("-5");
        year_name.add("-4");
        year_name.add("-3");
        year_name.add("-2");
        year_name.add("-1");
        year_name.add("0");

        day_chart1.setData(year_data);
        day_chart1.setNoDataText("");
        year_data.setBarWidth(barWidth);
        day_chart1.groupBars(1, groupspace, barSpace);

        // x축.

        XAxis year_xAxis = day_chart1.getXAxis();

        year_xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        year_xAxis.setGranularity(1);
        year_xAxis.setCenterAxisLabels(true);
        year_xAxis.setAxisMinimum(1);
        year_xAxis.setAxisMaximum(13);
        year_xAxis.setValueFormatter(new IndexAxisValueFormatter(year_name));

    }

    private void setupDayChart2() {     // Bar chart
        day_chart2 = (BarChart) findViewById(R.id.day_chart2);
        day_chart2.setNoDataText("");
        day_chart2.setDrawBarShadow(false);
        day_chart2.setDrawValueAboveBar(true);
        day_chart2.setMaxVisibleValueCount(50);
        day_chart2.setPinchZoom(false);
        day_chart2.setDrawGridBackground(true);

        try {
            new HttpUtil().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Log.v("결과",sleep_list.size()+"");
        ArrayList<Entry> yVals1 = new ArrayList<>();
        String[] temp = new String[3];
        String[] temp1 = new String[3];
        Log.v("결과1",temp1.length+"");
        ArrayList<Float> float_dt = new ArrayList<Float>();
        ArrayList<Float> float_st = new ArrayList<Float>();
        Log.v("결과1", sleep_list.size()+"");
        for (int i = 0; i <  sleep_list.size(); i++) {
            temp = sleep_list.get(i).getDatetime().split("-");

            for(int j = 0; j <  temp.length; j+=3){
                float_dt.add(Float.parseFloat(temp[j]+temp[j+1]+temp[j+1]));
            }
        }
        Log.v("결과1",temp.length+"");


        for (int i = 0; i <  sleep_list.size(); i++) {

            float_st.add(Float.parseFloat(sleep_list.get(i).getSleep_amount()));

        }
        ArrayList<BarEntry> year_barEntries = new ArrayList<>();
        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries.add(new BarEntry(i-7, float_st.get(i).floatValue()));
        }


        ArrayList<BarEntry> year_barEntries2 = new ArrayList<>();
        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries2.add(new BarEntry(i-7, Float.parseFloat(sleep_list.get(i).getLight()+20+"")));
        }

        ArrayList<BarEntry> year_barEntries3 = new ArrayList<>();

        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries2.add(new BarEntry(i-7, Float.parseFloat(sleep_list.get(i).getDb()+70+"")));
        }

        BarDataSet year_barDataSet = new BarDataSet(year_barEntries, "수면량");
        year_barDataSet.setColor(Color.parseColor("#81E4FD"));

        BarDataSet year_barDataSet2 = new BarDataSet(year_barEntries2, "조도");
        year_barDataSet2.setColor(Color.parseColor("#FDA5A7"));

        BarDataSet year_barDataSet3 = new BarDataSet(year_barEntries3, "소리");
        year_barDataSet3.setColor(Color.parseColor("#FDA5A7"));

        BarData year_data = new BarData(year_barDataSet, year_barDataSet2, year_barDataSet3);

        float groupspace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        ArrayList<String> year_name = new ArrayList<>();

        year_name.add("-7");
        year_name.add("-6");
        year_name.add("-5");
        year_name.add("-4");
        year_name.add("-3");
        year_name.add("-2");
        year_name.add("-1");
        year_name.add("0");

        day_chart2.setData(year_data);
        day_chart2.setNoDataText("");
        year_data.setBarWidth(barWidth);
        day_chart2.groupBars(1, groupspace, barSpace);

        // x축.

        XAxis year_xAxis = day_chart2.getXAxis();

        year_xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        year_xAxis.setGranularity(1);
        year_xAxis.setCenterAxisLabels(true);
        year_xAxis.setAxisMinimum(1);
        year_xAxis.setAxisMaximum(13);
        year_xAxis.setValueFormatter(new IndexAxisValueFormatter(year_name));

    }

    private void setData(int count, int range) {        // Line chart

        chart_single1 = (LineChart) findViewById(R.id.chart_single1);
        chart_single1.setNoDataText("");

        try {
            new HttpUtil().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }

        Log.v("결과",sleep_list.size()+"");
        ArrayList<Entry> yVals1 = new ArrayList<>();
        String[] temp = new String[3];
        String[] temp1 = new String[3];
        Log.v("결과1",temp1.length+"");
        ArrayList<Float> float_dt = new ArrayList<Float>();
        ArrayList<Float> float_wt = new ArrayList<Float>();
        Log.v("결과1", sleep_list.size()+"");
        for (int i = 0; i <  sleep_list.size(); i++) {
            temp = sleep_list.get(i).getDatetime().split("-");

            for(int j = 0; j <  temp.length; j+=3){
                float_dt.add(Float.parseFloat(temp[j]+temp[j+1]+temp[j+1]));
            }
        }
        Log.v("결과1",temp.length+"");


        for (int i = 0; i <  sleep_list.size(); i++) {
            temp1 = sleep_list.get(i).getWakeup_time().split(":");


            for(int j = 0; j <  temp1.length; j+=3){
                float_wt.add(Float.parseFloat(temp1[j+1]+temp1[j+2]));
            }
        }
        Log.v("결과1",temp1.length+"");
        Log.v("결과1",float_dt.size()+"");
        Log.v("결과1",float_wt.size()+"");
        for (int i = 0; i <  float_dt.size(); i++) {

            yVals1.add(new Entry(i-7,float_wt.get(i).floatValue()));
        }


        LineDataSet set1;

        set1 = new LineDataSet(yVals1, "기상 시간");



        LineData data = new LineData(set1);

        chart_single1.setData(data);
        chart_single1.setNoDataText("");

    }


    private void setData2(int count, int range) {       // Line chart

        chart_single2 = (LineChart) findViewById(R.id.chart_single2);
        chart_single2.setNoDataText("");


        try {
            new HttpUtil30().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        ArrayList<Entry> yVals1 = new ArrayList<>();

        String[] temp = new String[3];
        String[] temp1 = new String[3];
        Log.v("결과1",temp1.length+"");
        ArrayList<Float> float_dt = new ArrayList<Float>();
        ArrayList<Float> float_wt = new ArrayList<Float>();
        Log.v("결과1", sleep_list.size()+"");
        for (int i = 0; i <  sleep_list.size(); i++) {
            temp = sleep_list.get(i).getDatetime().split("-");

            for(int j = 0; j <  temp.length; j+=3){
                float_dt.add(Float.parseFloat(temp[j]+temp[j+1]+temp[j+1]));
            }
        }
        Log.v("결과1",temp.length+"");


        for (int i = 0; i <  sleep_list.size(); i++) {
            temp1 = sleep_list.get(i).getWakeup_time().split(":");


            for(int j = 0; j <  temp1.length; j+=3){
                float_wt.add(Float.parseFloat(temp1[j+1]+temp1[j+2]));
            }
        }
        Log.v("결과1",temp1.length+"");
        Log.v("결과1",float_dt.size()+"");
        Log.v("결과1",float_wt.size()+"");
        for (int i = 0; i <  float_dt.size(); i++) {

            yVals1.add(new Entry(i-30,float_wt.get(i).floatValue()));
        }




        LineDataSet set1;

        set1 = new LineDataSet(yVals1, "기상 시간");


        LineData data = new LineData(set1);

        chart_single2.setData(data);
        chart_single2.setNoDataText("");
    }

    private void setData3(int count, int range) {       // Line chart

        chart_single3 = (LineChart) findViewById(R.id.chart_single3);
        chart_single3.setNoDataText("");


        try {
            new HttpUtil().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Log.v("결과",sleep_list.size()+"");
        ArrayList<Entry> yVals1 = new ArrayList<>();
        String[] temp = new String[3];
        String[] temp1 = new String[3];
        Log.v("결과1",temp1.length+"");
        ArrayList<Float> float_dt = new ArrayList<Float>();
        ArrayList<Float> float_st = new ArrayList<Float>();
        Log.v("결과1", sleep_list.size()+"");
        for (int i = 0; i <  sleep_list.size(); i++) {
            temp = sleep_list.get(i).getDatetime().split("-");

            for(int j = 0; j <  temp.length; j+=3){
                float_dt.add(Float.parseFloat(temp[j]+temp[j+1]+temp[j+1]));
            }
        }
        Log.v("결과1",temp.length+"");


        for (int i = 0; i <  sleep_list.size(); i++) {
            temp1 = sleep_list.get(i).getSleep_time().split(":");


            for(int j = 0; j <  temp1.length; j+=3){
                float_st.add(Float.parseFloat(temp1[j+1]+temp1[j+2]));
            }
        }
        Log.v("결과1",temp1.length+"");
        Log.v("결과1",float_dt.size()+"");
        Log.v("결과1",float_st.size()+"");
        for (int i = 0; i <  float_dt.size(); i++) {

            yVals1.add(new Entry(i-7,float_st.get(i).floatValue()));
        }

        LineDataSet set1;

        set1 = new LineDataSet(yVals1, "취침 시간");

        LineData data = new LineData(set1);

        chart_single3.setData(data);
        chart_single3.setNoDataText("");
    }

    private void setData4(int count, int range) {       // Line chart

        chart_single4 = (LineChart) findViewById(R.id.chart_single4);
        chart_single4.setNoDataText("");

        try {
            new HttpUtil().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Log.v("결과",sleep_list.size()+"");
        ArrayList<Entry> yVals1 = new ArrayList<>();
        String[] temp = new String[3];
        String[] temp1 = new String[3];
        Log.v("결과1",temp1.length+"");
        ArrayList<Float> float_dt = new ArrayList<Float>();
        ArrayList<Float> float_st = new ArrayList<Float>();
        Log.v("결과1", sleep_list.size()+"");
        for (int i = 0; i <  sleep_list.size(); i++) {
            temp = sleep_list.get(i).getDatetime().split("-");

            for(int j = 0; j <  temp.length; j+=3){
                float_dt.add(Float.parseFloat(temp[j]+temp[j+1]+temp[j+1]));
            }
        }
        Log.v("결과1",temp.length+"");


        for (int i = 0; i <  sleep_list.size(); i++) {
            temp1 = sleep_list.get(i).getSleep_time().split(":");


            for(int j = 0; j <  temp1.length; j+=3){
                float_st.add(Float.parseFloat(temp1[j+1]+temp1[j+2]));
            }
        }
        Log.v("결과1",temp1.length+"");
        Log.v("결과1",float_dt.size()+"");
        Log.v("결과1",float_st.size()+"");
        for (int i = 0; i <  float_dt.size(); i++) {

            yVals1.add(new Entry(i-30,float_st.get(i).floatValue()));
        }
        LineDataSet set1;

        set1 = new LineDataSet(yVals1, "취침 시간");


        LineData data = new LineData(set1);

        chart_single4.setData(data);
    }

    private void setupDayChart3() {
        day_chart3 = (BarChart) findViewById(R.id.day_chart3);
        day_chart3.setNoDataText("");
        day_chart3.setDrawBarShadow(false);
        day_chart3.setDrawValueAboveBar(true);
        day_chart3.setMaxVisibleValueCount(50);
        day_chart3.setPinchZoom(false);
        day_chart3.setDrawGridBackground(true);

        try {
            new HttpUtil().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Log.v("결과",sleep_list.size()+"");
        ArrayList<Entry> yVals1 = new ArrayList<>();
        String[] temp = new String[3];
        String[] temp1 = new String[3];
        Log.v("결과1",temp1.length+"");
        ArrayList<Float> float_dt = new ArrayList<Float>();
        ArrayList<Float> float_st = new ArrayList<Float>();
        Log.v("결과1", sleep_list.size()+"");
        for (int i = 0; i <  sleep_list.size(); i++) {
            temp = sleep_list.get(i).getDatetime().split("-");

            for(int j = 0; j <  temp.length; j+=3){
                float_dt.add(Float.parseFloat(temp[j]+temp[j+1]+temp[j+1]));
            }
        }
        Log.v("결과1",temp.length+"");


        for (int i = 0; i <  sleep_list.size(); i++) {

                float_st.add(Float.parseFloat(sleep_list.get(i).getSleep_amount()));

        }
        ArrayList<BarEntry> year_barEntries = new ArrayList<>();
        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries.add(new BarEntry(i-7, float_st.get(i).floatValue()));
        }


        ArrayList<BarEntry> year_barEntries2 = new ArrayList<>();
        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries2.add(new BarEntry(i-7, Float.parseFloat(sleep_list.get(i).getLight()+"")));
        }

        ArrayList<BarEntry> year_barEntries3 = new ArrayList<>();

        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries2.add(new BarEntry(i-7, Float.parseFloat(sleep_list.get(i).getDb()+"")));
        }

        BarDataSet year_barDataSet = new BarDataSet(year_barEntries, "수면량");
        year_barDataSet.setColor(Color.parseColor("#81E4FD"));

        BarDataSet year_barDataSet2 = new BarDataSet(year_barEntries2, "조도");
        year_barDataSet2.setColor(Color.parseColor("#FDA5A7"));

        BarDataSet year_barDataSet3 = new BarDataSet(year_barEntries3, "소리");
        year_barDataSet3.setColor(Color.parseColor("#FDA5A7"));

        BarData year_data = new BarData(year_barDataSet, year_barDataSet2, year_barDataSet3);

        float groupspace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        ArrayList<String> year_name = new ArrayList<>();

        year_name.add("-7");
        year_name.add("-6");
        year_name.add("-5");
        year_name.add("-4");
        year_name.add("-3");
        year_name.add("-2");
        year_name.add("-1");
        year_name.add("0");

        day_chart3.setData(year_data);
        day_chart3.setNoDataText("");
        year_data.setBarWidth(barWidth);
        day_chart3.groupBars(1, groupspace, barSpace);

        // x축.

        XAxis year_xAxis = day_chart3.getXAxis();

        year_xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        year_xAxis.setGranularity(1);
        year_xAxis.setCenterAxisLabels(true);
        year_xAxis.setAxisMinimum(1);
        year_xAxis.setAxisMaximum(13);
        year_xAxis.setValueFormatter(new IndexAxisValueFormatter(year_name));

    }

    private void setupDayChart4() {
        day_chart4 = (BarChart) findViewById(R.id.day_chart4);
        day_chart4.setNoDataText("");
        day_chart4.setDrawBarShadow(false);
        day_chart4.setDrawValueAboveBar(true);
        day_chart4.setMaxVisibleValueCount(50);
        day_chart4.setPinchZoom(false);
        day_chart4.setDrawGridBackground(true);

        try {
            new HttpUtil().execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Log.v("결과",sleep_list.size()+"");
        ArrayList<Entry> yVals1 = new ArrayList<>();
        String[] temp = new String[3];
        String[] temp1 = new String[3];
        Log.v("결과1",temp1.length+"");
        ArrayList<Float> float_dt = new ArrayList<Float>();
        ArrayList<Float> float_st = new ArrayList<Float>();
        Log.v("결과1", sleep_list.size()+"");
        for (int i = 0; i <  sleep_list.size(); i++) {
            temp = sleep_list.get(i).getDatetime().split("-");

            for(int j = 0; j <  temp.length; j+=3){
                float_dt.add(Float.parseFloat(temp[j]+temp[j+1]+temp[j+1]));
            }
        }
        Log.v("결과1",temp.length+"");


        for (int i = 0; i <  sleep_list.size(); i++) {

            float_st.add(Float.parseFloat(sleep_list.get(i).getSleep_amount()));

        }
        ArrayList<BarEntry> year_barEntries = new ArrayList<>();
        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries.add(new BarEntry(i-7, float_st.get(i).floatValue()));
        }


        ArrayList<BarEntry> year_barEntries2 = new ArrayList<>();
        for (int i = 0; i <  float_dt.size(); i++) {
            year_barEntries2.add(new BarEntry(i-7, Float.parseFloat(sleep_list.get(i).getLight()+"")));
        }



        BarDataSet year_barDataSet = new BarDataSet(year_barEntries, "집중 시간");
        year_barDataSet.setColor(Color.parseColor("#81E4FD"));

        BarDataSet year_barDataSet2 = new BarDataSet(year_barEntries2, "조도");
        year_barDataSet2.setColor(Color.parseColor("#FDA5A7"));


        BarData year_data = new BarData(year_barDataSet, year_barDataSet2);

        float groupspace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        ArrayList<String> year_name = new ArrayList<>();

        year_name.add("-7");
        year_name.add("-6");
        year_name.add("-5");
        year_name.add("-4");
        year_name.add("-3");
        year_name.add("-2");
        year_name.add("-1");
        year_name.add("0");

        day_chart4.setData(year_data);
        day_chart4.setNoDataText("");
        year_data.setBarWidth(barWidth);
        day_chart4.groupBars(1, groupspace, barSpace);

        // x축.

        XAxis year_xAxis = day_chart4.getXAxis();

        year_xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        year_xAxis.setGranularity(1);
        year_xAxis.setCenterAxisLabels(true);
        year_xAxis.setAxisMinimum(1);
        year_xAxis.setAxisMaximum(13);
        year_xAxis.setValueFormatter(new IndexAxisValueFormatter(year_name));
    }


    public class HttpUtil extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuffer response = null;

            try {


                String url = "http://192.168.0.18:8080/Thrr/AndCom?type=vision_write&vision_write=5&id=" + email + "&startTime="+get7Time()+"&endTime="+getTime();
                Log.v("결과",email);
                Log.v("결과",get7Time());
                Log.v("결과",getTime());
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);

                conn.connect();

                int retCode = conn.getResponseCode();

                InputStream is = conn.getInputStream();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
                String line;
                response = new StringBuffer();

                while ((line = bfr.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                bfr.close();

            } catch (Exception e) {

            }

            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            //res = response.toString();   //여기로 JSON값이 들어옴

            Gson gson = new Gson();
            java.lang.reflect.Type type1 = new TypeToken<ArrayList<SleepDTO>>() {
            }.getType();


            String temp = result.toString();


            sleep_list = gson.fromJson(temp, type1);
            for (int i = 0; i < sleep_list.size(); i++) {
                Log.v("ss", sleep_list.get(i).getSelect_ASMR() + "/성공");
            }
        }

    }
    public class HttpUtil2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuffer response = null;

            try {


                String url = "http://192.168.0.18:8080/Thrr/AndCom?type=vision_write&vision_write=6&id=" + email + "&startTime="+get7Time()+"&endTime="+getTime();
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);

                conn.connect();

                int retCode = conn.getResponseCode();

                InputStream is = conn.getInputStream();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
                String line;
                response = new StringBuffer();

                while ((line = bfr.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                bfr.close();

            } catch (Exception e) {

            }

            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            //res = response.toString();   //여기로 JSON값이 들어옴

            Gson gson = new Gson();
            java.lang.reflect.Type type1 = new TypeToken<ArrayList<FocusDTO>>() {
            }.getType();


            String temp = result.toString();


            focus_list = gson.fromJson(temp, type1);
            for (int i = 0; i < focus_list.size(); i++) {
                Log.v("ss", focus_list.get(i).getSelect_ASMR() + "/성공");

            }
        }

    }
    public class HttpUtil30 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuffer response = null;

            try {


                String url = "http://192.168.0.18:8080/Thrr/AndCom?type=vision_write&vision_write=5&id=" + email + "&startTime=" + get30Time() + "&endTime=" + getTime();
                Log.v("결과", email);
                Log.v("결과", get7Time());
                Log.v("결과", getTime());
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);

                conn.connect();

                int retCode = conn.getResponseCode();

                InputStream is = conn.getInputStream();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
                String line;
                response = new StringBuffer();

                while ((line = bfr.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                bfr.close();

            } catch (Exception e) {

            }

            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            //res = response.toString();   //여기로 JSON값이 들어옴

            Gson gson = new Gson();
            java.lang.reflect.Type type1 = new TypeToken<ArrayList<SleepDTO>>() {
            }.getType();


            String temp = result.toString();


            sleep_list = gson.fromJson(temp, type1);
            for (int i = 0; i < sleep_list.size(); i++) {
                Log.v("ss", sleep_list.get(i).getSelect_ASMR() + "/성공");
            }
        }
    }
    public class HttpUtil302 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuffer response = null;

            try {


                String url = "http://192.168.0.18:8080/Thrr/AndCom?type=vision_write&vision_write=6&id=" + email + "&startTime="+get30Time()+"&endTime="+getTime();
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);

                conn.connect();

                int retCode = conn.getResponseCode();

                InputStream is = conn.getInputStream();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
                String line;
                response = new StringBuffer();

                while ((line = bfr.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                bfr.close();

            } catch (Exception e) {

            }

            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            //res = response.toString();   //여기로 JSON값이 들어옴

            Gson gson = new Gson();
            java.lang.reflect.Type type1 = new TypeToken<ArrayList<FocusDTO>>() {
            }.getType();


            String temp = result.toString();


            focus_list = gson.fromJson(temp, type1);
            for (int i = 0; i < focus_list.size(); i++) {
                Log.v("ss", focus_list.get(i).getSelect_ASMR() + "/성공");

            }
        }

    }
        private String get7Time(){
        mNow = System.currentTimeMillis()-604800800;  // 현재 시간 가져오기.
        mDate = new Date(mNow);     // 데이트 생성하기.
        Log.v("get7Time",mNow+"");
        Log.v("get7Time",mFormat.format(mDate));
        return mFormat.format(mDate);
    }

    private String getTime(){
        mNow2 = System.currentTimeMillis();  // 현재 시간 가져오기.
        mDate2 = new Date(mNow2);     // 데이트 생성하기.
        return mFormat2.format(mDate2);
    }
    private String get30Time(){
        mNow3 = System.currentTimeMillis()-Long.parseLong("2592000000");  // 현재 시간 가져오기.
        mDate3 = new Date(mNow3);     // 데이트 생성하기.
        Log.v("get7Time",mNow3+"");
        Log.v("get7Time",mFormat.format(mDate));
        return mFormat3.format(mDate3);

    }

}
