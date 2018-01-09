package thrr.asmr.finalproject.com.thrr;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class YearChartActivity extends AppCompatActivity {

    BarChart year_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        year_chart = (BarChart) findViewById(R.id.year_chart);

        year_chart.setDrawBarShadow(false);
        year_chart.setDrawValueAboveBar(true);
        year_chart.setMaxVisibleValueCount(50);
        year_chart.setPinchZoom(false);
        year_chart.setDrawGridBackground(true);

        ArrayList<BarEntry> year_barEntries = new ArrayList<>();

        year_barEntries.add(new BarEntry(1, 0));
        year_barEntries.add(new BarEntry(2, 0));
        year_barEntries.add(new BarEntry(3, 0));
        year_barEntries.add(new BarEntry(4, 0));
        year_barEntries.add(new BarEntry(6, 40f));
        year_barEntries.add(new BarEntry(5, 44f));
        year_barEntries.add(new BarEntry(7, 30f));
        year_barEntries.add(new BarEntry(8, 36f));
        year_barEntries.add(new BarEntry(9, 40f));
        year_barEntries.add(new BarEntry(10, 44f));
        year_barEntries.add(new BarEntry(11, 30f));
        year_barEntries.add(new BarEntry(12, 36f));

        ArrayList<BarEntry> year_barEntries2 = new ArrayList<>();

        year_barEntries2.add(new BarEntry(1, 0));
        year_barEntries2.add(new BarEntry(2, 0));
        year_barEntries2.add(new BarEntry(3, 0));
        year_barEntries2.add(new BarEntry(4, 0));
        year_barEntries2.add(new BarEntry(5, 43f));
        year_barEntries2.add(new BarEntry(6, 54f));
        year_barEntries2.add(new BarEntry(7, 60f));
        year_barEntries2.add(new BarEntry(8, 31f));
        year_barEntries2.add(new BarEntry(9, 43f));
        year_barEntries2.add(new BarEntry(10, 54f));
        year_barEntries2.add(new BarEntry(11, 60f));
        year_barEntries2.add(new BarEntry(12, 31f));

        BarDataSet year_barDataSet = new BarDataSet(year_barEntries2, "수입");
        year_barDataSet.setColor(Color.BLUE);

        BarDataSet year_barDataSet2 = new BarDataSet(year_barEntries2, "지출");
        year_barDataSet2.setColor(Color.RED);

        BarData year_data = new BarData(year_barDataSet, year_barDataSet2);

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
        year_name.add("8월");
        year_name.add("9월");
        year_name.add("10월");
        year_name.add("11월");
        year_name.add("12월");

        year_chart.setData(year_data);
        year_data.setBarWidth(barWidth);
        year_chart.groupBars(1, groupspace, barSpace);

        XAxis year_xAxis = year_chart.getXAxis();

        year_xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        year_xAxis.setGranularity(1);
        year_xAxis.setCenterAxisLabels(true);
        year_xAxis.setAxisMinimum(1);
        year_xAxis.setAxisMaximum(13);
        year_xAxis.setValueFormatter(new IndexAxisValueFormatter(year_name));


    }


}
