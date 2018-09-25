package com.example.jerwynalqueza.feelsbook;

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
            for (int i = 0; i < emotionList.size(); i++) {
                countList.add(emotionList.get(i).getCount());
                emotionNameList.add(emotionList.get(i).getName());
            }
        }
    }

    static public ArrayList<Emotion> getEmotionList(){
        return emotionList;
    }

    static public ArrayList<String> getNameList(){
        if (emotionList == null) {
            ArrayList<Emotion> holder = getEmotionList();
        }
        return emotionNameList;
    }
    static public ArrayList<Integer> getCountList() {
        if (countList == null) {
            ArrayList<Emotion> holder = getEmotionList();
        }
        return countList;
    }

    public void addEntry(String emotion, String date, String comment){

    }
}
