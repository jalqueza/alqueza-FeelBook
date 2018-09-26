package com.example.jerwynalqueza.feelsbook;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class EmotionsController {


    private static ArrayList<Emotion> emotionList = null;
    private static ArrayList<String> emotionNameList = null;
    private static ArrayList<Integer> countList = null;

    static public void check(){
        if (emotionList == null) {
            emotionList = new EmotionList().getEmotionList();
            emotionNameList = new ArrayList<String>();
            countList = new ArrayList<Integer>();
            for (int i = 0; i < emotionList.size(); i++) {
                countList.add(emotionList.get(i).getCount());
                emotionNameList.add(emotionList.get(i).getName());
            }
        }
    }

    static public ArrayList<Emotion> getEmotionList(){
        check();
        return emotionList;
    }
    static public ArrayList<String> getNameList(){
        check();
        return emotionNameList;
    }
    static public ArrayList<Integer> getCountList() {
        check();
        return countList;
    }

    public void addEntry(String emotion, String date, String comment){
        check();
        Entry entry = new Entry(emotion, date, comment, emotionList);
        entry.addCommentDate();
    }
}
