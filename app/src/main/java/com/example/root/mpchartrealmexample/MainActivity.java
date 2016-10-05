package com.example.root.mpchartrealmexample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.realm.implementation.RealmBarDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import layout.GraficoBarra;

public class MainActivity extends AppCompatActivity implements GraficoBarra.dibujar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(fabOnClick);

        FloatingActionButton graph = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        graph.setOnClickListener(graficar);
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
            dibujar();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void dibujar() {
        //GraficoBarra gb = (GraficoBarra) getFragmentManager().findFragmentById(R.id.graficoBarraFragment);

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

        RealmBarDataSet<Score> dataSet = new RealmBarDataSet<>(results, "scoreNr", "totalScore");

        ArrayList<IBarDataSet> dataSetList = new ArrayList<IBarDataSet>();
        dataSetList.add(dataSet); // add the dataset

        BarData data = new BarData(dataSetList);

        //barChart.setData(data);
        //barChart.invalidate();

        //Context ctx = getActivity().getApplicationContext();
        //Toast toast = Toast.makeText(this.getApplicationContext(), String.valueOf(results.size()), Toast.LENGTH_LONG);
        //toast.show();
        //Snackbar.make(getView(), String.valueOf(results.size()), Snackbar.LENGTH_LONG)
        //       .setAction("Action", null).show();

    }
}
