package com.example.android.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText searchText;
    public SharedPreferences searchPrefs;
    ArrayAdapter searchAdapter;
    ArrayList<String> historyList, responseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchPrefs = getApplicationContext().getSharedPreferences("Searches", MODE_PRIVATE);
        SharedPreferences.Editor edit = searchPrefs.edit();

        searchText = (EditText) findViewById(R.id.search);
        String search = searchText.toString();
        searchText.addTextChangedListener(textWatcher);

        int size = searchPrefs.getAll().size();

        if (size > 0) {
//            edit.putString(Integer.toString(size), search.toString());
//            edit.commit();
//            final ArrayList<String> list = new ArrayList<>();
//            Map<String, ?> entries = searchPrefs.getAll();
//            Set<String> keys = entries.keySet();
//            for(String key : keys)
//            {
//                list.add(searchPrefs.getString(key, ""));
//            }
        }

        historyList = new ArrayList<>();
        edit.putString("1", "book");
        edit.putString("2", "car");
        edit.putString("3", "pen");
        edit.putString("4", "bike");
        edit.commit();
        historyList.add(searchPrefs.getString("1", ""));
        historyList.add(searchPrefs.getString("2", ""));
        historyList.add(searchPrefs.getString("3", ""));
        historyList.add(searchPrefs.getString("4", ""));

        searchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);

        final ListView listView = (ListView) findViewById(R.id.searchId);
        listView.setAdapter(searchAdapter);

        findViewById(R.id.clearHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyList.clear();
                SharedPreferences searches = getSharedPreferences("Searches", MODE_PRIVATE);
                SharedPreferences.Editor editor = searches.edit();
                editor.clear();
                searchAdapter.clear();
                listView.setAdapter(null);
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String editText = searchText.getText().toString();
            if (TextUtils.isEmpty(editText)) {
                ListView listView = (ListView) findViewById(R.id.searchId);
                ArrayAdapter searchAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, historyList);
                listView.setAdapter(searchAdapter);
                Log.v("Empty edit text", "Empty edit text");
            } else {
                responseList = new ArrayList<>();
                responseList.add("Travel");
                responseList.add("TravelKhana");
                responseList.add("Travel VISA");
                responseList.add("Travel XP");
                responseList.add("TravelMob");
                responseList.add("TravelEasy");
                responseList.add("RiyaTravels");
                searchAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, responseList);
                ListView listView = (ListView) findViewById(R.id.searchId);
                listView.setAdapter(searchAdapter);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
