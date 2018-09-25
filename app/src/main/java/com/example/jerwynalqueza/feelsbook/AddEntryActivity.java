package com.example.jerwynalqueza.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AddEntryActivity extends AppCompatActivity {

    private String emotionString;
    private EmotionsController ec = new EmotionsController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        Bundle b = getIntent().getExtras();
        if(b != null)
            emotionString = b.getString("Emotion");
    }

    public void skip(View v){
        String date = new Date().toString();
        ec.addEntry(emotionString, date, "");
        finish();
    }

    public void addComment(View v){
        String date = new Date().toString();
        EditText comment = (EditText) findViewById(R.id.commentText);
        ec.addEntry(emotionString, date, comment.getText().toString());
        finish();
    }
}
