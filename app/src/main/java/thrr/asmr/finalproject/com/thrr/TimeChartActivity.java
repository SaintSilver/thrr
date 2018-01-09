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

public class TimeChartActivity extends AppCompatActivity {

    private BarChart time_chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_chart);

        time_chart = (BarChart) findViewById(R.id.time_chart);
        time_chart.getDescription().setEnabled(false);

        Legend time_l = time_chart.getLegend();
        time_l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        time_l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        time_l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        time_l.setDrawInside(false);
        time_l.setForm(Legend.LegendForm.SQUARE);
        time_l.setFormSize(9f);
        time_l.setTextSize(11f);
        time_l.setXEntrySpace(4f);


        time_chart.setFitBars(true);

        XAxis time_xAxis = time_chart.getXAxis();


        ArrayList<BarEntry> time_yVals = new ArrayList<>();

        time_yVals.add(new BarEntry(1, 10));
        time_yVals.add(new BarEntry(2, 3));
        time_yVals.add(new BarEntry(3, 20));
        time_yVals.add(new BarEntry(4, 10));
        time_yVals.add(new BarEntry(6, 40f));
        time_yVals.add(new BarEntry(5, 44f));
        time_yVals.add(new BarEntry(7, 30f));
        time_yVals.add(new BarEntry(8, 30f));

        BarDataSet time_set = new BarDataSet(time_yVals,"Time set");
        time_set.setColors(ColorTemplate.MATERIAL_COLORS);
        time_set.setDrawValues(true);

        BarData time_data = new BarData(time_set);

        time_chart.setData(time_data);
        time_chart.invalidate();
        time_chart.animateY(500);

        ArrayList<String> time_name = new ArrayList<>();
        time_name.add("");
        time_name.add("00~03");
        time_name.add("03~06");
        time_name.add("06~09");
        time_name.add("09~12");
        time_name.add("12~15");
        time_name.add("15~18");
        time_name.add("18~21");
        time_name.add("21~00");

        time_xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        time_xAxis.setValueFormatter(new IndexAxisValueFormatter(time_name));
    }
}
