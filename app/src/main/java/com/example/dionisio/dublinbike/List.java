package com.example.dionisio.dublinbike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView mylistView = (ListView)findViewById(R.id.listView);



        ArrayList<String> listView = new ArrayList<String>();
        listView.add("");




        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listView);

       mylistView.setAdapter(arrayAdapter);
    }
}