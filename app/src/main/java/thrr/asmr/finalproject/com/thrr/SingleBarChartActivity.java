package thrr.asmr.finalproject.com.thrr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class SingleBarChartActivity extends AppCompatActivity {

    LineChart lineChart;
    Button chartbtn_1, chartbtn_2, chartbtn_3, chartbtn_4, chartbtn_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        lineChart = (LineChart) findViewById(R.id.chart_single);


        setData(40, 60);
        lineChart.animateX(1000);

    }

    private void setData(int count, int range) {

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