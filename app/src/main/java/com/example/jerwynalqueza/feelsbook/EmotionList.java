package com.example.jerwynalqueza.feelsbook;

import java.util.ArrayList;

public class EmotionList {

    // Class that stores an ArrayList of Emotion Objects

    private ArrayList<Emotion> emotionList;

    public EmotionList(){
        emotionList = new ArrayList<Emotion>();

        // add each Emotion object into emotionList
        emotionList.add(new Emotion("Love"));
        emotionList.add(new Emotion("Joy"));
        emotionList.add(new Emotion("Surprise"));
        emotionList.add(new Emotion("Anger"));
        emotionList.add(new Emotion("Sadness"));
        emotionList.add(new Emotion("Fear"));
    }

    public ArrayList<Emotion> getEmotionList(){
        return emotionList;
    }
    public void setEmotionList(ArrayList<Emotion> emotionList){this.emotionList = emotionList;}
}