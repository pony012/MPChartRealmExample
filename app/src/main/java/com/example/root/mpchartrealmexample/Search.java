package com.example.root.mpchartrealmexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.app.SearchManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class Search extends AppCompatActivity {

    private ListView list;
    private SearchView search;
   // private String query;
    ArrayAdapter<String> adapter;
    ArrayList araylist;
    String industries[];
    EditText edit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = (SearchView) findViewById(R.id.searchTextIndustria);
        list = (ListView) findViewById(R.id.listadoIndustrias);
       // industries = getResources().getStringArray(R.array.Industry_Name);
        queryIntentnVerify();

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener())

    }

//---------------------------------------------------------------------------------------------------------------------------
    // Get the intent, verify the action and get the query
        public String queryIntentnVerify() {
            industries = getResources().getStringArray(R.array.Industry_Name);
            araylist = new ArrayList(Arrays.asList(industries));
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, araylist);
            list.setAdapter(adapter);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
        return query;
        }

        public void listViewRecive(String query){


        }
















        //*** setOnQueryTextFocusChangeListener ***
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //  Auto-generated method stub

                Toast.makeText(getBaseContext(), String.valueOf(hasFocus),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Auto-generated method stub

                Toast.makeText(getBaseContext(), query,
                        Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Auto-generated method stub

                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void doMySearch(String query) {
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_search, menu);
//        return true;
//    }
}
