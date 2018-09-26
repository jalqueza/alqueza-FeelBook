package com.example.jerwynalqueza.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ArrayList<String> entryStrings = new ArrayList<String>();

        for(int i = 0; i < EmotionsController.getEntryList().size(); i++){
            String string;
            if (EmotionsController.getEntryList().get(i).getComment() == "")
                string = EmotionsController.getEntryList().get(i).getEmotion();
            else
                string = EmotionsController.getEntryList().get(i).getEmotion() + ": " + EmotionsController.getEntryList().get(i).getComment();
            entryStrings.add(string);
        }

        ListView historyListView = (ListView) findViewById(R.id.historyListView);
        ArrayAdapter<String> entryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, entryStrings);
        historyListView.setAdapter(entryAdapter);

        // delete, edit comment, edit date
        // date has to be actual date

        // sort by date get EntryList then sort by date (get code online)
        // keep counts (sql)
    }
}
