/*
    FeelsBook: add and record emotions
    Copyright (C) 2018  Jerwyn Alqueza alqueza@ualberta.ca

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.example.jerwynalqueza.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {


    public static final String FILENAME1 = "emotionlist.sav";
    public static final String FILENAME2 = "entrylist.sav";

    private EmotionsController ec = new EmotionsController();

    public static String timezone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);
        loadFromFile();


        Calendar cal = Calendar.getInstance();
        long milliDiff = cal.get(Calendar.ZONE_OFFSET);
        // Got local offset, now loop through available timezone id(s).
        String [] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            TimeZone tz = TimeZone.getTimeZone(id);
            if (tz.getRawOffset() == milliDiff) {
                // Found a match.
                timezone = id;
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.statsMenu:
                openStatsActivity();
                return true;
            case R.id.historyMenu:
                openHistoryActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addEntryActivity(View v){
        Intent intent = new Intent(this, AddEntryActivity.class);
        Bundle b = new Bundle();

        // Pass Emotion name into AddEntryActivity
        if(v.getId() == R.id.addLoveButton)
            b.putString("Emotion", "Love");
        else if(v.getId() == R.id.addAngerButton)
            b.putString("Emotion", "Anger");
        else if(v.getId() == R.id.addJoyButton)
            b.putString("Emotion", "Joy");
        else if(v.getId() == R.id.addFearButton)
            b.putString("Emotion", "Fear");
        else if(v.getId() == R.id.addSadnessButton)
            b.putString("Emotion", "Sadness");
        else if (v.getId() == R.id.addSurpriseButton)
            b.putString("Emotion", "Surprise");

        intent.putExtras(b);
        startActivity(intent);
    }

    public void openStatsActivity() {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }


    public void openHistoryActivity(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }


    private void loadFromFile() {

        try {

            FileInputStream fis1 = openFileInput(FILENAME1);
            FileInputStream fis2 = openFileInput(FILENAME2);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(fis1));
            BufferedReader in2 = new BufferedReader(new InputStreamReader(fis2));
            Gson gson = new Gson();


            Type listType = new TypeToken<ArrayList<Emotion>>() {}.getType();
            ArrayList<Emotion> emotionList = new ArrayList<Emotion>();
            emotionList = gson.fromJson(in1, listType);
            Type listType2 =  new TypeToken<ArrayList<Entry>>() {}.getType();
            ArrayList<Entry> entryList = new ArrayList<Entry>();
            entryList = gson.fromJson(in2, listType2);

            EmotionsController.loadFile(emotionList, entryList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}