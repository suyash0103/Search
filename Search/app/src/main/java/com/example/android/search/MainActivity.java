package com.example.android.search;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText searchText;
    public SharedPreferences searchPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = (EditText) findViewById(R.id.search);
        String search = searchText.toString();

        searchPrefs = getApplicationContext().getSharedPreferences("Searches", MODE_PRIVATE);
        SharedPreferences.Editor edit = searchPrefs.edit();

        edit.putString("1", "book");
        edit.putString("2", "car");
        edit.putString("3", "pen");
        edit.putString("4", "bike");
        edit.commit();

        ArrayList<String> list = new ArrayList<>();
        list.add(searchPrefs.getString("1", ""));
        list.add(searchPrefs.getString("2", ""));
        list.add(searchPrefs.getString("3", ""));
        list.add(searchPrefs.getString("4", ""));

        ArrayAdapter<?> searchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        ListView listView = (ListView) findViewById(R.id.searchId);
        listView.setAdapter(searchAdapter);

    }
}
