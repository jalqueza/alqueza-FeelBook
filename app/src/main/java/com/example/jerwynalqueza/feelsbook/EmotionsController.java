package com.example.jerwynalqueza.feelsbook;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class EmotionsController {


    private static ArrayList<Emotion> emotionList = null;
    private static ArrayList<String> emotionNameList = null;
    private static ArrayList<Integer> countList = null;


    public void init(){
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

    public ArrayList<Emotion> getEmotionList(){
        return emotionList;
    }

    public ArrayList<String> getNameList(){
        init();
        return emotionNameList;
    }
    public ArrayList<Integer> getCountList() {
        init();
        return countList;
    }

    public void addEntry(String emotion, String date, String comment){
        Entry entry = new Entry(emotion, date, comment, emotionList);
        entry.addCommentDate();
    }
}
