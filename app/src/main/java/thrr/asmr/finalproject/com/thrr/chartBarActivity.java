package thrr.asmr.finalproject.com.thrr;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class chartBarActivity extends AppCompatActivity {

    BarChart barChart;
    Button chartbtn_1, chartbtn_2, chartbtn_3, chartbtn_4, chartbtn_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        findViewById(R.id.chartbtn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setupChartBar();

            }
        });
    }

    private void setupChartBar() {

        barChart = (BarChart) findViewById(R.id.chart_bar);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, 0));
        barEntries.add(new BarEntry(2, 0));
        barEntries.add(new BarEntry(3, 0));
        barEntries.add(new BarEntry(4, 0));
        barEntries.add(new BarEntry(6, 40f));
        barEntries.add(new BarEntry(5, 44f));
        barEntries.add(new BarEntry(7, 30f));
        barEntries.add(new BarEntry(8, 36f));
        barEntries.add(new BarEntry(9, 40f));
        barEntries.add(new BarEntry(10, 44f));
        barEntries.add(new BarEntry(11, 30f));
        barEntries.add(new BarEntry(12, 36f));

        ArrayList<BarEntry> barEntries1 = new ArrayList<>();

        barEntries1.add(new BarEntry(1, 0));
        barEntries1.add(new BarEntry(2, 0));
        barEntries1.add(new BarEntry(3, 0));
        barEntries1.add(new BarEntry(4, 0));
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

}

