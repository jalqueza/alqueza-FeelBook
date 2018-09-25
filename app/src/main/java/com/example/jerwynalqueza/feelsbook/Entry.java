package com.example.jerwynalqueza.feelsbook;

import android.util.Log;

import java.util.ArrayList;

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

    // Get matching index in emotionList and then matching index of date in dateList
    public ArrayList<Integer> findEmotionAndDateIndex(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(findEmotionIndex());
        for (int i = 0; i < emotionList.get(list.get(0)).getDates().size(); i++) {
            if (this.date.equals(emotionList.get(list.get(0)).getDates().get(i))) {
                list.add(i);
                break;
            }
        }
        return list;
    }

    public void addCommentDate() {
        int index = findEmotionIndex();
        emotionList.get(index).addEntry(this.comment, this.date);
    }
    public void deleteCommentDate() {
        ArrayList<Integer> indexes = findEmotionAndDateIndex();
        emotionList.get(indexes.get(0)).deleteEntry(this.comment, this.date, indexes.get(1));
    }
    public void editComment(String newComment) {
        ArrayList<Integer> indexes = findEmotionAndDateIndex();
        emotionList.get(indexes.get(0)).editEntry(newComment, indexes.get(1));
    }


}