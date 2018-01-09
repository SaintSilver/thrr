package thrr.asmr.finalproject.com.thrr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DayChartActivity extends AppCompatActivity {

    private BarChart day_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_chart);

        day_chart = (BarChart) findViewById(R.id.day_chart);
        day_chart.getDescription().setEnabled(false);

        Legend day_l = day_chart.getLegend();
        day_l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        day_l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        day_l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        day_l.setDrawInside(false);
        day_l.setForm(Legend.LegendForm.SQUARE);
        day_l.setFormSize(9f);
        day_l.setTextSize(11f);
        day_l.setXEntrySpace(4f);


        day_chart.setFitBars(true);

        XAxis xAxis = day_chart.getXAxis();


        ArrayList<BarEntry> day_yVals = new ArrayList<>();

        day_yVals.add(new BarEntry(1, 10));
        day_yVals.add(new BarEntry(2, 3));
        day_yVals.add(new BarEntry(3, 20));
        day_yVals.add(new BarEntry(4, 10));
        day_yVals.add(new BarEntry(6, 40f));
        day_yVals.add(new BarEntry(5, 44f));
        day_yVals.add(new BarEntry(7, 30f));

        BarDataSet day_set = new BarDataSet(day_yVals,"Data set");
        day_set.setColors(ColorTemplate.MATERIAL_COLORS);
        day_set.setDrawValues(true);

        BarData day_data = new BarData(day_set);

        day_chart.setData(day_data);
        day_chart.invalidate();
        day_chart.animateY(500);

        ArrayList<String> day_name = new ArrayList<>();
        day_name.add("");
        day_name.add("일");
        day_name.add("월");
        day_name.add("화");
        day_name.add("수");
        day_name.add("목");
        day_name.add("금");
        day_name.add("토");

        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(day_name));
    }



}