package com.example.jerwynalqueza.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class AddEmotionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);
    }

    public void addEntryActivity(View v){
        Toast.makeText(this, "Add Comment", Toast.LENGTH_SHORT).show();

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
}
