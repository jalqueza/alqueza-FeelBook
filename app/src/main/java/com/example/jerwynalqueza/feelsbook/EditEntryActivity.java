package com.example.jerwynalqueza.feelsbook;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
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

public class EditEntryActivity extends AppCompatActivity {

    private int index;
    private Entry editEntry;

    private TextView commentView;
    private String newComment;
    private String comment;

    private String dateOfEntry;
    private TextView dateView;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Date newDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            index = b.getInt("Index");
            dateOfEntry = b.getString("Date");
        }

        editEntry = EmotionsController.getEntryList().get(index);

        comment = editEntry.getComment();
        commentView = (TextView) findViewById(R.id.newCommentText);
        commentView.setText(comment);

        dateView = (TextView) findViewById(R.id.dateTextView);
        dateView.setText(dateOfEntry);

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditEntryActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });



        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                newDate = new Date(year-1900, month, dayOfMonth);
                TimeZone tz = TimeZone.getTimeZone("UTC");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
                df.setTimeZone(tz);
                String ISOdate = df.format(newDate);
                dateView.setText(ISOdate);
            }
        };
    }

    public void delete(View v) {
        EmotionsController.deleteEntry(editEntry);
        Toast.makeText(EditEntryActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
        saveInFile();
        finish();
    }

    public void editComment(View v) {
        newComment = commentView.getText().toString();
        if (newComment.length() > 100) {
            Toast.makeText(EditEntryActivity.this, "Error: Comment Limit is 100 Char", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newComment != comment) {
            EmotionsController.editComment(editEntry, newComment);
            saveInFile();
            Toast.makeText(EditEntryActivity.this, "Comment Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void editDate(View v){
        if (newDate != null){
            EmotionsController.editDate(editEntry, newDate);
            saveInFile();
            Toast.makeText(EditEntryActivity.this, "Date Changed", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInFile() {
        try {


            FileOutputStream fos1 = openFileOutput(MainActivity.FILENAME1, 0);
            FileOutputStream fos2 = openFileOutput(MainActivity.FILENAME2, 0);
            OutputStreamWriter writer1 = new OutputStreamWriter(fos1);
            OutputStreamWriter writer2 = new OutputStreamWriter(fos2);
            Gson gson = new Gson();

            gson.toJson(EmotionsController.getEmotionList(), writer1);
            gson.toJson(EmotionsController.getEntryList(), writer2);


            writer1.flush();
            writer2.flush();
            fos1.close();
            fos2.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}