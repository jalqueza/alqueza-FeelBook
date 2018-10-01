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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class EditEntryActivity extends AppCompatActivity {

    private int index;

    private TextView commentView;
    private String fullComment; // with emotion
    private String newComment = null;
    private String [] oldComment;

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
            fullComment = b.getString("String");
            dateOfEntry = b.getString("Date");
        }
        oldComment = fullComment.split(": ", 2);

        if (oldComment.length == 2) {
            commentView = (TextView) findViewById(R.id.newCommentText);
            commentView.setText(oldComment[1]);
        }


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
        Entry deleteEntry = EmotionsController.getEntryList().get(index);
        EmotionsController.deleteEntry(deleteEntry);
        Toast.makeText(EditEntryActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void editComment(View v) {
        Entry editEntry = EmotionsController.getEntryList().get(index);
        commentView = (TextView) findViewById(R.id.newCommentText);
        String newComment = commentView.getText().toString();
        if (newComment == null)
            EmotionsController.editComment(editEntry, "");
        else
            EmotionsController.editComment(editEntry, newComment);
        Toast.makeText(EditEntryActivity.this, "Comment Changed", Toast.LENGTH_SHORT).show();
    }

    public void editDate(View v){

        Entry editEntry = EmotionsController.getEntryList().get(index);
        if (newDate != null){
            EmotionsController.editDate(editEntry, newDate);
            Toast.makeText(EditEntryActivity.this, "Date Changed", Toast.LENGTH_SHORT).show();
        }
    }
}