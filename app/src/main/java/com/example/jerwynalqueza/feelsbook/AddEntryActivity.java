package com.example.jerwynalqueza.feelsbook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AddEntryActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    // Activity where Entry is first automatically added; then delete entry, add comment and edit date options are available

    private String emotionString;

    private TextView commentView;
    private String newComment = null;

    private TextView dateView;
    private Date newDate = null;
    private int yearFinal, monthFinal, dayFinal;

    private Entry entry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        Bundle b = getIntent().getExtras();
        if(b != null)
            emotionString = b.getString("Emotion");

        Date date = new Date();

        EmotionsController.addEntry(emotionString, date, "");
        saveInFile();
        Toast.makeText(this, "Entry Added", Toast.LENGTH_SHORT).show();

        int index = EmotionsController.getEntryListObj().getEntryList().size() - 1;
        entry = EmotionsController.getEntryListObj().getEntryList().get(index);

        // Converting time to ISO format
        TimeZone tz = TimeZone.getTimeZone(MainActivity.timezone);
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        df.setTimeZone(tz);
        String ISOdate = df.format(date);
        dateView = (TextView) findViewById(R.id.dateTextView);
        dateView.setText(ISOdate);

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddEntryActivity.this,
                        AddEntryActivity.this,
                        year, month, day);
                dialog.show();

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month;
        dayFinal = dayOfMonth;
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(AddEntryActivity.this,AddEntryActivity.this,
                hour, minute, android.text.format.DateFormat.is24HourFormat(this));
        dialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Change TextView of date if time is set

        newDate = new Date(yearFinal-1900, monthFinal, dayFinal, hourOfDay, minute);
        TimeZone tz = TimeZone.getTimeZone(MainActivity.timezone);
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        String ISOdate = df.format(newDate);
        dateView.setText(ISOdate);
    }


    // ENTRY OPTIONS

    public void delete(View v) {
        EmotionsController.deleteEntry(entry);
        Toast.makeText(AddEntryActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
        saveInFile();
        finish();
    }

    public void editComment(View v) {

        commentView = (TextView) findViewById(R.id.newCommentText);
        newComment = commentView.getText().toString();
        if (newComment.length() > 100) {
            Toast.makeText(AddEntryActivity.this, "Error: Comment Limit is 100 Char", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newComment.equals(entry.getComment())){ // empty comment
            entry.setComment(newComment);
            saveInFile();
            Toast.makeText(AddEntryActivity.this, "Comment Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void editDate(View v){

        if (newDate != null){
            entry.setDate(newDate);
            saveInFile();
            Toast.makeText(AddEntryActivity.this, "Date Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInFile() {
        try {


            FileOutputStream fos1 = openFileOutput(MainActivity.FILENAME1, 0);
            FileOutputStream fos2 = openFileOutput(MainActivity.FILENAME2, 0);
            OutputStreamWriter writer1 = new OutputStreamWriter(fos1);
            OutputStreamWriter writer2 = new OutputStreamWriter(fos2);
            Gson gson = new Gson();

            gson.toJson(EmotionsController.getEmotionListObj().getEmotionList(), writer1);
            gson.toJson(EmotionsController.getEntryListObj().getEntryList(), writer2);


            writer1.flush();
            writer2.flush();
            fos1.close();
            fos2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
