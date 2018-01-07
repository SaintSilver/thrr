package thrr.asmr.finalproject.com.thrr;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    Button chartbtn_1, chartbtn_2, chartbtn_3, chartbtn_4, chartbtn_5;

    PieChart chart_pie = null;
    BarChart chart_bar = null;
    LineChart chart_single = null;

    BarChart barChart;
    LineChart lineChart;

    // 임시로 넣어둔 데이터에욤. (pie)
    float item_count[] = {1f, 1f, 1f, 1f , 1f};
    String item_name[] = {"100","200","300","400","500"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        chart_pie = (PieChart) findViewById(R.id.chart_pie);
        chart_bar = (BarChart) findViewById(R.id.chart_bar);
        chart_single = (LineChart) findViewById(R.id.chart_single);

        chartbtn_1 = findViewById(R.id.chartbtn_1);

        chartbtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setupPieChart();

                chart_pie.setVisibility(View.VISIBLE);
                chart_bar.setVisibility(View.INVISIBLE);
                chart_single.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.chartbtn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setupBarchart();

                chart_bar.setVisibility(View.VISIBLE);
                chart_pie.setVisibility(View.INVISIBLE);
                chart_single.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.chartbtn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 임시로 넣어둔 데이터에욤. (Line)
                setData(7, 12);
                lineChart.animateX(1000);

                chart_single.setVisibility(View.VISIBLE);
                chart_bar.setVisibility(View.INVISIBLE);
                chart_pie.setVisibility(View.INVISIBLE);

            }
        });
    }


    private void setupPieChart() {
        int arraySum = 0;

        for (int i =0; i<item_count.length;i++){
            arraySum+=item_count[i];
        }

        //Popultaing a list of PieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i =0;i<item_count.length;i++){
            pieEntries.add(new PieEntry((item_count[i]/arraySum*100), item_name[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "month");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the chart
        PieChart chart = (PieChart) findViewById(R.id.chart_pie);
        chart.setData(data);
        chart.invalidate();
    }

    private void setupBarchart() {

        barChart = (BarChart) findViewById(R.id.chart_bar);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, 11));
        barEntries.add(new BarEntry(2, 9));
        barEntries.add(new BarEntry(3, 7));
        barEntries.add(new BarEntry(4, 8));
        barEntries.add(new BarEntry(6, 40f));
        barEntries.add(new BarEntry(5, 44f));
        barEntries.add(new BarEntry(7, 30f));
        barEntries.add(new BarEntry(8, 36f));
        barEntries.add(new BarEntry(9, 40f));
        barEntries.add(new BarEntry(10, 44f));
        barEntries.add(new BarEntry(11, 30f));
        barEntries.add(new BarEntry(12, 36f));

        ArrayList<BarEntry> barEntries1 = new ArrayList<>();

        barEntries1.add(new BarEntry(1, 3));
        barEntries1.add(new BarEntry(2, 6));
        barEntries1.add(new BarEntry(3, 8));
        barEntries1.add(new BarEntry(4, 9));
        barEntries1.add(new BarEntry(5, 43f));
        barEntries1.add(new BarEntry(6, 54f));
        barEntries1.add(new BarEntry(7, 60f));
        barEntries1.add(new BarEntry(8, 31f));
        barEntries1.add(new BarEntry(9, 43f));
        barEntries1.add(new BarEntry(10, 54f));
        barEntries1.add(new BarEntry(11, 60f));
        barEntries1.add(new BarEntry(12, 31f));

        BarDataSet barDataSet = new BarDataSet(barEntries, "수입");
        barDataSet.setColor(Color.BLUE);

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "지출");
        barDataSet1.setColor(Color.RED);

        BarData data = new BarData(barDataSet, barDataSet1);

        float groupspace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        barChart.setData(data);
        data.setBarWidth(barWidth);
        barChart.groupBars(1, groupspace, barSpace);

        XAxis xAxis = barChart.getXAxis();

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart);

        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);
        xAxis.setAxisMaximum(13);

    }

    private void setData(int count, int range) {

        lineChart = (LineChart) findViewById(R.id.chart_single);

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

        lineChart.setData(data);

    }

}

