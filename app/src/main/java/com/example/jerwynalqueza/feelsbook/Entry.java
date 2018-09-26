package com.example.jerwynalqueza.feelsbook;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class Entry {

    private String emotion;
    private String date;
    private String comment;
    private ArrayList<Emotion> emotionList;


    public Entry(String emotion, String date, String comment, ArrayList<Emotion> emotionList) {
        this.emotion = emotion;
        this.date = date;
        this.comment = comment;
        this.emotionList = emotionList;
    }

    public String getEmotion(){ return emotion; }
    public String getComment(){ return comment; }
    public ArrayList<Emotion> getEmotionList(){ return emotionList;}

    // Get matching index in emotionList
    public int findEmotionIndex() {
        int index = 10;
        for (int i = 0; i < emotionList.size(); i++) {
            if (this.emotion.equals(emotionList.get(i).getName())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void increaseCount() {
        int index = findEmotionIndex();
        emotionList.get(index).increaseCount();
    }
    public void decreaseCount() {
        int index = findEmotionIndex();
        emotionList.get(index).decreaseCount();
    }
}