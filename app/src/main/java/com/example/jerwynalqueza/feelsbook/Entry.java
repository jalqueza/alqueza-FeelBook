package com.example.jerwynalqueza.feelsbook;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class Entry {

    // Class that holds emotion name, date and comment

    private String emotion;
    private Date date;
    private String comment;


    public Entry(String emotion, Date date, String comment) {
        this.emotion = emotion;
        this.date = date;
        this.comment = comment;
    }

    public String getEmotion(){ return emotion; }
    public String getComment(){ return comment; }
    public Date getDate(){ return date; }

    public void setComment(String comment){
        this.comment = comment;
    }
    public void setDate(Date date){
        this.date = date;
    }

    // Get matching index in emotionList
    private int findEmotionIndex(ArrayList<Emotion> emotionList) {
        int index = 10;
        for (int i = 0; i < emotionList.size(); i++) {
            if (this.emotion.equals(emotionList.get(i).getName())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void increaseCount(ArrayList<Emotion> emotionList) {
        int index = findEmotionIndex(emotionList);
        emotionList.get(index).increaseCount();
    }
    public void decreaseCount(ArrayList<Emotion> emotionList) {
        int index = findEmotionIndex(emotionList);
        emotionList.get(index).decreaseCount();
    }
}