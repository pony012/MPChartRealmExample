package com.example.root.mpchartrealmexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    LineChart timeLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);

//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .schemaVersion(2) // Must be bumped when the schema changes
//                .build();

//        Realm realm = Realm.getInstance(config);
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<UserProfile> results = realm.where(UserProfile.class).findAll();
        if(results.size()>0){
            setContentView(R.layout.activity_main);

            timeLine = (LineChart) findViewById(R.id.BarChart);
            timeLine.invalidate();
            Legend l = timeLine.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
            l.setDrawInside(false);

            XAxis xAxis = timeLine.getXAxis();
            xAxis.setValueFormatter(new AxisValueFormatter(){
                private SimpleDateFormat mFormat = new SimpleDateFormat("MMM yy");
                @Override
                public String getFormattedValue(float value, AxisBase axis){
                    long millis = TimeUnit.DAYS.toMillis((long) value);
                    return mFormat.format(new Date(millis));
                }

                @Override
                public int getDecimalDigits(){
                    return 0;
                }
            });

            YAxis leftAxis = timeLine.getAxisLeft();

            YAxis rightAxis = timeLine.getAxisRight();
            rightAxis.setEnabled(false);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(fabOnClick);

            FloatingActionButton graph = (FloatingActionButton) findViewById(R.id.floatingActionButton);
            graph.setOnClickListener(graficar);
        }else{
            Intent intent = new Intent(this, Welcome.class);
            startActivity(intent);
            finish();
            //setContentView(R.layout.activity_welcome);

        }
    }

    final android.view.View.OnClickListener fabOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();

            Score score1 = new Score(100f, 0f, "Peter");
            realm.copyToRealm(score1);
            Score score2 = new Score(110f, 1f, "Lisa");
            realm.copyToRealm(score2);
            Score score3 = new Score(130f, 2f, "Dennis");
            realm.copyToRealm(score3);
            Score score4 = new Score(70f, 3f, "Luke");
            realm.copyToRealm(score4);
            Score score5 = new Score(80f, 4f, "Sarah");
            realm.copyToRealm(score5);

            realm.commitTransaction();

            Snackbar.make(view, realm.toString(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };

    protected float getRandom(float range, float startsfrom) {

        return (float) (Math.random() * range) + startsfrom;
    }

    private int[] mColors = new int[] {
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2],
            ColorTemplate.VORDIPLOM_COLORS[3]
    };

    private String[] mTexto = new String[]{
            "Mercado",
            "Tu empresa",
            "Cetes"
    };
    final android.view.View.OnClickListener graficar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Realm realm = Realm.getDefaultInstance();
//            final RealmResults<Score> results = realm.where(Score.class).findAll();
//
//            AxisValueFormatter formatter = new AxisValueFormatter() {
//                @Override
//                public String getFormattedValue(float value, AxisBase axis) {
//                    return results.get((int) value).getPlayerName();
//                }
//
//                @Override
//                public int getDecimalDigits() {
//                    return 0;
//                }
//            };
//
//            RealmLineDataSet<Score> dataSet = new RealmLineDataSet<>(results, "scoreNr", "totalScore");
//
//            ArrayList<ILineDataSet> dataSetList = new ArrayList<>();
//            dataSetList.add(dataSet); // add the dataset
//
//            LineData data = new LineData(dataSetList);
//
//            LineChart lineChart = (LineChart) findViewById(R.id.BarChart);
//            lineChart.setData(data);
//            lineChart.invalidate();
//
//            Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(results.size()), Toast.LENGTH_LONG);
//            toast.show();
            long now = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis());

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

            for (int z = 0; z < 3; z++) {
//                Log.i("Fecha", (new SimpleDateFormat("dd-MM-yyyy")).format(new Date(TimeUnit.DAYS.toMillis(now))));
                ArrayList<Entry> values = new ArrayList<Entry>();

                float from = now;

//                float to = now + 24;
                // increment by 1 day

                for (float x = 0; x < 24; x++) {

                    float y = getRandom(50, -20);
                    values.add(new Entry(from + (x * 30), y)); // add one entry per hour
                }

                // create a dataset and give it a type
                LineDataSet set1 = new LineDataSet(values, mTexto[z%mTexto.length]);
                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                set1.setLineWidth(1.5f);
                set1.setDrawCircles(false);
                set1.setDrawValues(false);
                set1.setFillAlpha(65);
                set1.setFillColor(ColorTemplate.getHoloBlue());
                set1.setHighLightColor(Color.rgb(244, 117, 117));
                set1.setDrawCircleHole(false);

                int color = mColors[z % mColors.length];
                set1.setColor(color);
                set1.setCircleColor(color);
                dataSets.add(set1);
            }


            // create a data object with the datasets
            LineData data = new LineData(dataSets);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            // set data
            timeLine.setData(data);
            timeLine.invalidate();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(this ,"Settings", Toast.LENGTH_SHORT);
            toast.show();
            Intent seleccionarArea = new Intent(this, seleccion_area.class);
            startActivity(seleccionarArea);
            return true;
        }*/
        if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(this ,"Settings", Toast.LENGTH_SHORT);
            toast.show();
            Intent busqueda = new Intent(this, Search.class);
            startActivity(busqueda);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
