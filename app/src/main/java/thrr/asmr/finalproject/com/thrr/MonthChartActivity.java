package thrr.asmr.finalproject.com.thrr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MonthChartActivity extends AppCompatActivity {

    float item_count[] = {98.8f, 12.8f, 161.6f, 242.2f , 52f};
    String item_name[] = {"1","2","3","4","5"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPieChart();
    }

    private void setupPieChart() {
        int month_arraySum = 0;
        for (int i =0; i<item_count.length;i++){
            month_arraySum+=item_count[i];
        }

        //Popultaing a list of PieEntries
        List<PieEntry> month_pieEntries = new ArrayList<>();
        for (int i =0;i<item_count.length;i++){
            month_pieEntries.add(new PieEntry((item_count[i]/month_arraySum*100), item_name[i]));
        }

        PieDataSet month_dataSet = new PieDataSet(month_pieEntries, "month");
        month_dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData month_data = new PieData(month_dataSet);

        //Get the chart
        PieChart month_chart = (PieChart) findViewById(R.id.month_chart);
        month_chart.setData(month_data);
        month_chart.invalidate();
    }
}
