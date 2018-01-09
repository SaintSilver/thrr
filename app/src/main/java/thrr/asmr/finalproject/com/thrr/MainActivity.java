package thrr.asmr.finalproject.com.thrr;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private LineChart chart_single1, chart_single2, chart_single3, chart_single4;
    private BarChart day_chart1, day_chart2, day_chart3, day_chart4;

    private TextView textView_date ;
    private ImageView imageView_m, imageView_p ;
    private Button chartbtn_1, chartbtn_2, chartbtn_3, chartbtn_4, chartbtn_5, chartbtn_6, chartbtn_7, chartbtn_8;
    private SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* === 차트창에 접속하면 DB에서 email에 해당하는 회원 데이터 싹 긁어모아와서 setData하셈. === */
        spf = getSharedPreferences("emailspf", MODE_PRIVATE);
        String email = spf.getString("email", ""); // 이메일




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

        if(chart_single1 != null) chart_single1.setVisibility(View.VISIBLE);
        if(chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
        if(chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
        if(chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
        if(day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
        if(day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
        if(day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
        if(day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);


        findViewById(R.id.chartbtn_1).setOnClickListener(new View.OnClickListener() {       // 기상 시간 (일주일)
            @Override
            public void onClick(View v) {

                setData(7, 12);
                chart_single1.animateX(1000);

                if(chart_single1 != null) chart_single1.setVisibility(View.VISIBLE);
                if(chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if(chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if(chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if(day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if(day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if(day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if(day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_2).setOnClickListener(new View.OnClickListener() {       // 기상 시간 (한달)
            @Override
            public void onClick(View v) {

                setData2(7, 12);
                chart_single2.animateX(1000);
                chart_single2.setNoDataText("");

                if(chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if(chart_single2 != null) chart_single2.setVisibility(View.VISIBLE);
                if(chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if(chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if(day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if(day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if(day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if(day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.chartbtn_3).setOnClickListener(new View.OnClickListener() {       //  취침 시간 (일주일)
            @Override
            public void onClick(View v) {

                setData3(7, 12);
                chart_single2.animateX(1000);
                chart_single2.setNoDataText("");


                if(chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if(chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if(chart_single3 != null) chart_single3.setVisibility(View.VISIBLE);
                if(chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if(day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if(day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if(day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if(day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.chartbtn_4).setOnClickListener(new View.OnClickListener() {       //  취침 시간 (한달)
            @Override
            public void onClick(View v) {

                setData4(7, 12);
                chart_single2.animateX(1000);
                chart_single2.setNoDataText("");


                if(chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if(chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if(chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if(chart_single4 != null) chart_single4.setVisibility(View.VISIBLE);
                if(day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if(day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if(day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if(day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.chartbtn_5).setOnClickListener(new View.OnClickListener() {       // 크기, 밝기, 수면량
            @Override
            public void onClick(View v) {

                setupDayChart3();

                if(chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if(chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if(chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if(chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if(day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if(day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if(day_chart3 != null) day_chart3.setVisibility(View.VISIBLE);
                if(day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_6).setOnClickListener(new View.OnClickListener() {       // 집중 (일주일)
            @Override
            public void onClick(View v) {

                setupDayChart();

                if(chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if(chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if(chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if(chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if(day_chart1 != null) day_chart1.setVisibility(View.VISIBLE);
                if(day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if(day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if(day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_7).setOnClickListener(new View.OnClickListener() {       // 집중 (한달)
            @Override
            public void onClick(View v) {

                setupDayChart2();

                if(chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if(chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if(chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if(chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if(day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if(day_chart2 != null) day_chart2.setVisibility(View.VISIBLE);
                if(day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if(day_chart4 != null) day_chart4.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_8).setOnClickListener(new View.OnClickListener() {       // 크기, 밝기, 날짜별 집중시간
            @Override
            public void onClick(View v) {

                setupDayChart4();

                if(chart_single1 != null) chart_single1.setVisibility(View.INVISIBLE);
                if(chart_single2 != null) chart_single2.setVisibility(View.INVISIBLE);
                if(chart_single3 != null) chart_single3.setVisibility(View.INVISIBLE);
                if(chart_single4 != null) chart_single4.setVisibility(View.INVISIBLE);
                if(day_chart1 != null) day_chart1.setVisibility(View.INVISIBLE);
                if(day_chart2 != null) day_chart2.setVisibility(View.INVISIBLE);
                if(day_chart3 != null) day_chart3.setVisibility(View.INVISIBLE);
                if(day_chart4 != null) day_chart4.setVisibility(View.VISIBLE);
            }
        });
    } //onCreate 메소드 끝

    private void setupDayChart() {      // Bar chart
        day_chart1 = (BarChart) findViewById(R.id.day_chart1);
        day_chart1.getDescription().setEnabled(false);

        day_chart1.setFitBars(true);

        XAxis xAxis = day_chart1.getXAxis();


        ArrayList<BarEntry> day_yVals = new ArrayList<>();

        day_yVals.add(new BarEntry(1, 10));
        day_yVals.add(new BarEntry(2, 3));
        day_yVals.add(new BarEntry(3, 20));
        day_yVals.add(new BarEntry(4, 10));
        day_yVals.add(new BarEntry(6, 40f));
        day_yVals.add(new BarEntry(5, 44f));
        day_yVals.add(new BarEntry(7, 30f));

        BarDataSet day_set = new BarDataSet(day_yVals,"");
        day_set.setColor(Color.RED);
        day_set.setDrawValues(true);

        BarData day_data = new BarData(day_set);

        day_chart1.setData(day_data);
        day_chart1.setNoDataText("");
        day_chart1.invalidate();
        day_chart1.animateY(500);

        ArrayList<String> day_name = new ArrayList<>();
        day_name.add("");
        day_name.add("일");
        day_name.add("월");
        day_name.add("화");
        day_name.add("수");
        day_name.add("목");
        day_name.add("금");
        day_name.add("토");

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(day_name));

    }

    private void setupDayChart2() {     // Bar chart

        day_chart2 = (BarChart) findViewById(R.id.day_chart2);
        day_chart2.getDescription().setEnabled(false);

        day_chart2.setFitBars(true);

        XAxis xAxis = day_chart2.getXAxis();


        ArrayList<BarEntry> day_yVals = new ArrayList<>();

        day_yVals.add(new BarEntry(1, 30));
        day_yVals.add(new BarEntry(2, 4));
        day_yVals.add(new BarEntry(3, 50));
        day_yVals.add(new BarEntry(4, 10));
        day_yVals.add(new BarEntry(6, 40f));
        day_yVals.add(new BarEntry(5, 44f));
        day_yVals.add(new BarEntry(7, 30f));

        BarDataSet day_set = new BarDataSet(day_yVals,"");
        day_set.setColor(Color.GREEN);
        day_set.setDrawValues(true);

        BarData day_data = new BarData(day_set);

        day_chart2.setData(day_data);
        day_chart2.setNoDataText("");
        day_chart2.invalidate();
        day_chart2.animateY(500);

        ArrayList<String> day_name = new ArrayList<>();
        day_name.add("");
        day_name.add("일");
        day_name.add("월");
        day_name.add("화");
        day_name.add("수");
        day_name.add("목");
        day_name.add("금");
        day_name.add("토");

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(day_name));

    }

    private void setData(int count, int range) {        // Line chart

        chart_single1 = (LineChart) findViewById(R.id.chart_single1);
        chart_single1.setNoDataText("");

        ArrayList<Entry> yVals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals2.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals3 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        set1 = new LineDataSet(yVals1, "Hello yms");

        set2 = new LineDataSet(yVals2, "Hello mong");

        set3 = new LineDataSet(yVals3, "hello heelloo");

        LineData data = new LineData(set1, set2, set3);

        chart_single1.setData(data);
        chart_single1.setNoDataText("");

    }

    private void setData2(int count, int range) {       // Line chart

        chart_single2 = (LineChart) findViewById(R.id.chart_single2);
        chart_single2.setNoDataText("");

        ArrayList<Entry> yVals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals2.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals3 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        set1 = new LineDataSet(yVals1, "Hello yms");

        set2 = new LineDataSet(yVals2, "Hello mong");

        set3 = new LineDataSet(yVals3, "hello heelloo");

        LineData data = new LineData(set1, set2, set3);

        chart_single2.setData(data);
        chart_single2.setNoDataText("");
    }

    private void setData3(int count, int range) {       // Line chart

        chart_single3 = (LineChart) findViewById(R.id.chart_single3);
        chart_single3.setNoDataText("");

        ArrayList<Entry> yVals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals2.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals3 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        set1 = new LineDataSet(yVals1, "Hello yms");

        set2 = new LineDataSet(yVals2, "Hello mong");

        set3 = new LineDataSet(yVals3, "hello heelloo");

        LineData data = new LineData(set1, set2, set3);

        chart_single3.setData(data);
        chart_single3.setNoDataText("");
    }

    private void setData4(int count, int range) {       // Line chart

        chart_single4 = (LineChart) findViewById(R.id.chart_single4);
        chart_single4.setNoDataText("");

        ArrayList<Entry> yVals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals2.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals3 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 250;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        set1 = new LineDataSet(yVals1, "Hello yms");

        set2 = new LineDataSet(yVals2, "Hello mong");

        set3 = new LineDataSet(yVals3, "hello heelloo");

        LineData data = new LineData(set1, set2, set3);

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

        ArrayList<BarEntry> year_barEntries = new ArrayList<>();

        year_barEntries.add(new BarEntry(1, 0));
        year_barEntries.add(new BarEntry(2, 0));
        year_barEntries.add(new BarEntry(3, 0));
        year_barEntries.add(new BarEntry(4, 0));
        year_barEntries.add(new BarEntry(6, 40f));
        year_barEntries.add(new BarEntry(5, 44f));
        year_barEntries.add(new BarEntry(7, 30f));

        ArrayList<BarEntry> year_barEntries2 = new ArrayList<>();

        year_barEntries2.add(new BarEntry(1, 0));
        year_barEntries2.add(new BarEntry(2, 0));
        year_barEntries2.add(new BarEntry(3, 0));
        year_barEntries2.add(new BarEntry(4, 0));
        year_barEntries2.add(new BarEntry(5, 43f));
        year_barEntries2.add(new BarEntry(6, 54f));
        year_barEntries2.add(new BarEntry(7, 60f));

        ArrayList<BarEntry> year_barEntries3 = new ArrayList<>();

        year_barEntries3.add(new BarEntry(1, 0));
        year_barEntries3.add(new BarEntry(2, 0));
        year_barEntries3.add(new BarEntry(3, 0));
        year_barEntries3.add(new BarEntry(4, 0));
        year_barEntries3.add(new BarEntry(5, 43f));
        year_barEntries3.add(new BarEntry(6, 54f));
        year_barEntries3.add(new BarEntry(7, 60f));

        BarDataSet year_barDataSet = new BarDataSet(year_barEntries, "수입");
        year_barDataSet.setColor(Color.parseColor("#81E4FD"));

        BarDataSet year_barDataSet2 = new BarDataSet(year_barEntries2, "지출");
        year_barDataSet2.setColor(Color.parseColor("#FDA5A7"));

        BarDataSet year_barDataSet3 = new BarDataSet(year_barEntries3, "지출");
        year_barDataSet3.setColor(Color.parseColor("#FDA5A7"));

        BarData year_data = new BarData(year_barDataSet, year_barDataSet2, year_barDataSet3);

        float groupspace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        ArrayList<String> year_name = new ArrayList<>();
        year_name.add("");
        year_name.add("1월");
        year_name.add("2월");
        year_name.add("3월");
        year_name.add("4월");
        year_name.add("5월");
        year_name.add("6월");
        year_name.add("7월");

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

        ArrayList<BarEntry> year_barEntries = new ArrayList<>();

        year_barEntries.add(new BarEntry(1, 0));
        year_barEntries.add(new BarEntry(2, 0));
        year_barEntries.add(new BarEntry(3, 0));
        year_barEntries.add(new BarEntry(4, 0));
        year_barEntries.add(new BarEntry(6, 40f));
        year_barEntries.add(new BarEntry(5, 44f));
        year_barEntries.add(new BarEntry(7, 30f));

        ArrayList<BarEntry> year_barEntries2 = new ArrayList<>();

        year_barEntries2.add(new BarEntry(1, 0));
        year_barEntries2.add(new BarEntry(2, 0));
        year_barEntries2.add(new BarEntry(3, 0));
        year_barEntries2.add(new BarEntry(4, 0));
        year_barEntries2.add(new BarEntry(5, 43f));
        year_barEntries2.add(new BarEntry(6, 54f));
        year_barEntries2.add(new BarEntry(7, 60f));

        ArrayList<BarEntry> year_barEntries3 = new ArrayList<>();

        year_barEntries3.add(new BarEntry(1, 0));
        year_barEntries3.add(new BarEntry(2, 0));
        year_barEntries3.add(new BarEntry(3, 0));
        year_barEntries3.add(new BarEntry(4, 0));
        year_barEntries3.add(new BarEntry(5, 43f));
        year_barEntries3.add(new BarEntry(6, 54f));
        year_barEntries3.add(new BarEntry(7, 60f));

        BarDataSet year_barDataSet = new BarDataSet(year_barEntries, "수입");
        year_barDataSet.setColor(Color.parseColor("#81E4FD"));

        BarDataSet year_barDataSet2 = new BarDataSet(year_barEntries2, "지출");
        year_barDataSet2.setColor(Color.parseColor("#FDA5A7"));

        BarDataSet year_barDataSet3 = new BarDataSet(year_barEntries3, "지출");
        year_barDataSet3.setColor(Color.parseColor("#FDA5A7"));

        BarData year_data = new BarData(year_barDataSet, year_barDataSet2, year_barDataSet3);

        float groupspace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        ArrayList<String> year_name = new ArrayList<>();
        year_name.add("");
        year_name.add("1월");
        year_name.add("2월");
        year_name.add("3월");
        year_name.add("4월");
        year_name.add("5월");
        year_name.add("6월");
        year_name.add("7월");

        day_chart4.setData(year_data);
        year_data.setBarWidth(barWidth);
        day_chart4.groupBars(1, groupspace, barSpace);
        day_chart4.setNoDataText("");


        // x축.

        XAxis year_xAxis = day_chart4.getXAxis();

        year_xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        year_xAxis.setGranularity(1);
        year_xAxis.setCenterAxisLabels(true);
        year_xAxis.setAxisMinimum(1);
        year_xAxis.setAxisMaximum(13);
        year_xAxis.setValueFormatter(new IndexAxisValueFormatter(year_name));

    }

}
