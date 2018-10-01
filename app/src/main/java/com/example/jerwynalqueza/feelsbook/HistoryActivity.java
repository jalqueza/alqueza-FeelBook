package com.example.jerwynalqueza.feelsbook;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private ArrayList<String> entryStrings;
    private ArrayList<Date> datesList;

    private ArrayAdapter<String> entryAdapter;
    private ListView historyListView;

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_history);
        EmotionsController.sort();

        entryStrings = new ArrayList<String>();
        datesList = new ArrayList<Date>();

        for (int i = 0; i < EmotionsController.getEntryList().size() ; i++) {
            datesList.add(EmotionsController.getEntryList().get(i).getDate());
            String string;
            if (EmotionsController.getEntryList().get(i).getComment() == "")
                string = EmotionsController.getEntryList().get(i).getEmotion();
            else
                string = EmotionsController.getEntryList().get(i).getEmotion() + ": " + EmotionsController.getEntryList().get(i).getComment();
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
                        b.putInt("Index", position);
                        b.putString("String", String.valueOf(parent.getItemAtPosition(position)));

                        TimeZone tz = TimeZone.getTimeZone("UTC");
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
                        df.setTimeZone(tz);
                        String ISOdate = df.format(datesList.get(position));
                        b.putString("Date", ISOdate);

                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }
        );
    }

}
