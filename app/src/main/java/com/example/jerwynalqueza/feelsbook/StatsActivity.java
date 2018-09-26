package com.example.jerwynalqueza.feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    ArrayList<Integer> CountList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);




        /*
        Bundle b = getIntent().getExtras();
        if(b != null) {
            emotionNameList = b.getStringArrayList("emotionNameList");
            countList = b.getIntegerArrayList("countList");
        }
        */
        for(int i = 0; i < EmotionsController.getEmotionList().size(); i++ )
            CountList.add(EmotionsController.getEmotionList().get(i).getCount());

        ListAdapter emotionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, EmotionsController.getNameList());
        ListAdapter countAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, EmotionsController.getCountList());

        ListView emotionListView = (ListView) findViewById(R.id.emotionListView);
        ListView countListView = (ListView) findViewById(R.id.countListView);
        emotionListView.setAdapter(emotionAdapter);
        countListView.setAdapter(countAdapter);
    }
}
