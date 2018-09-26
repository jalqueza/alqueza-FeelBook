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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button addEmotionButton;
    private Button statsButton;
    private Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addEmotionButton = (Button) findViewById(R.id.addEmotionButton);
        addEmotionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAddEmotionActivity();
            }
        });

        statsButton = (Button) findViewById(R.id.statsButton);
        statsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openStatsActivity();
            }
        });

        historyButton = (Button) findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHistoryActivity();
            }
        });


    }

    public void openAddEmotionActivity(){
        Toast.makeText(this, "Add Emotion", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddEmotionActivity.class);
        startActivity(intent);
    }

    public void openStatsActivity(){
        Toast.makeText(this, "Stats", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, StatsActivity.class);
        /*
        EmotionsController ec = new EmotionsController();
        Bundle b = new Bundle();
        b.putStringArrayList("emotionNameList", ec.getNameList());
        b.putIntegerArrayList("countList", ec.getCountList());
        intent.putExtras(b);
        */

        Toast.makeText(this, EmotionsController.getEmotionList().get(1).getCount(), Toast.LENGTH_SHORT).show();
        //Log.d("STRING", EmotionsController.getEmotionList().get(1).getComments().get(1));
        startActivity(intent);
        }


    public void openHistoryActivity(){
        Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}