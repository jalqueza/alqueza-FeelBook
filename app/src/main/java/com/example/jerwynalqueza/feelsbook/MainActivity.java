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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);
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
}