package com.example.root.mpchartrealmexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class seleccion_area extends AppCompatActivity {

    private ListView industrias;
    ArrayAdapter<String> adapter;
    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_area);

        final String industries[] = getResources().getStringArray(R.array.Industry_Name);

        industrias = (ListView) findViewById(R.id.listadoIndustrias);
        inputSearch = (EditText) findViewById(R.id.editTextIndustria);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, industries);
        adapter.sort(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        industrias.setAdapter(adapter);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                seleccion_area.this.adapter.getFilter().filter(s);
            }
        });

        industrias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Presionó: ", String.valueOf(position));
                Log.d("Valor: ", adapter.getItem(position));
                Log.d("Posición en Array: ", String.valueOf(Arrays.asList(industries).indexOf(adapter.getItem(position))));
            }
        });
    }
}
