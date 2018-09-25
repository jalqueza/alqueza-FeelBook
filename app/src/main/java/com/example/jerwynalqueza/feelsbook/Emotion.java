package com.example.jerwynalqueza.feelsbook;

import android.util.Log;

import java.util.ArrayList;

public class Emotion {

    private String name;
    private String emoji;
    private int count = 0;
    private ArrayList<String> commentList =  new ArrayList<String>();
    private ArrayList<String> dateList =  new ArrayList<String>();


    public Emotion(String name, String emoji){
        this.name = name;
        this.emoji = emoji;
    }

    // Getters
    public String getName(){ return name; }
    public String getEmoji(){
        return emoji;
    }
    public int getCount(){
        return count;
    }
    public ArrayList<String> getComments(){ return commentList; }
    public ArrayList<String> getDates(){ return dateList; }

    // Entry Methods (Update commentList, dateList, count)
    public void addEntry(String comment, String date){
        commentList.add(comment);
        dateList.add(date);
        count++;
    }
    public void deleteEntry(String comment, String date, int index){
        dateList.remove(date);
        commentList.remove(index);
        count--;
    }
    public void editEntry(String newComment, int index){
        commentList.set(index, newComment);
    }
}