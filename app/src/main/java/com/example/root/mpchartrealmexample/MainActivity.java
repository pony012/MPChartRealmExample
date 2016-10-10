package com.example.root.mpchartrealmexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.realm.implementation.RealmLineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);

        Realm realm = Realm.getDefaultInstance();
        final RealmResults<UserProfile> results = realm.where(UserProfile.class).findAll();
        if(results.size()>0){
            setContentView(R.layout.activity_main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(fabOnClick);

            FloatingActionButton graph = (FloatingActionButton) findViewById(R.id.floatingActionButton);
            graph.setOnClickListener(graficar);
        }else{
            Intent intent = new Intent(this, Welcome.class);
            startActivity(intent);
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

    final android.view.View.OnClickListener graficar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Realm realm = Realm.getDefaultInstance();
            final RealmResults<Score> results = realm.where(Score.class).findAll();

            AxisValueFormatter formatter = new AxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return results.get((int) value).getPlayerName();
                }

                @Override
                public int getDecimalDigits() {
                    return 0;
                }
            };

            RealmLineDataSet<Score> dataSet = new RealmLineDataSet<>(results, "scoreNr", "totalScore");

            ArrayList<ILineDataSet> dataSetList = new ArrayList<>();
            dataSetList.add(dataSet); // add the dataset

            LineData data = new LineData(dataSetList);

            LineChart lineChart = (LineChart) findViewById(R.id.BarChart);
            lineChart.setData(data);
            lineChart.invalidate();

            Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(results.size()), Toast.LENGTH_LONG);
            toast.show();
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
        if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(this ,"Settings", Toast.LENGTH_SHORT);
            toast.show();
            Intent seleccionarArea = new Intent(this, seleccion_area.class);
            startActivity(seleccionarArea);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
