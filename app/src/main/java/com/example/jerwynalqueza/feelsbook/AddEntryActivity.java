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

    public void skipComment(View v){
        Date date = new Date();
        ec.addEntry(emotionString, date, "");
        Toast.makeText(this, "Entry Added", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void addComment(View v){
        Date date = new Date();
        EditText comment = (EditText) findViewById(R.id.commentText);
        ec.addEntry(emotionString, date, comment.getText().toString());
        Toast.makeText(this, "Entry Added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
