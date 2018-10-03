package com.example.jerwynalqueza.feelsbook;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class HistoryActivity extends AppCompatActivity {

    // Activity where entries are shown with date

    private ArrayList<String> entryStrings;
    private ArrayList<String> datesList;

    private ArrayAdapter<String> entryAdapter;
    private ListView historyListView;

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_history);
        EmotionsController.sort();

        entryStrings = new ArrayList<String>();
        datesList = new ArrayList<String>();

        for (int i = 0; i < EmotionsController.getEntryListObj().getEntryList().size() ; i++) {
            TimeZone tz = TimeZone.getTimeZone(MainActivity.timezone);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);
            String ISOdate = df.format(EmotionsController.getEntryListObj().getEntryList().get(i).getDate());
            datesList.add(ISOdate);
            String string;
            string = EmotionsController.getEntryListObj().getEntryList().get(i).getEmotion() + ": " + ISOdate;
            entryStrings.add(string);
        }

        historyListView = (ListView) findViewById(R.id.historyListView);
        entryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, entryStrings);
        historyListView.setAdapter(entryAdapter);

        historyListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(HistoryActivity.this, EditEntryActivity.class);
                        Bundle b = new Bundle();
                        // Passes the index of the entry in the entry list
                        b.putInt("Index", position);
                        b.putString("Date", datesList.get(position));
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }
        );
    }

}
