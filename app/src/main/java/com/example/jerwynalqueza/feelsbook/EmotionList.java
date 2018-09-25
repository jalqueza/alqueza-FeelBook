package com.example.jerwynalqueza.feelsbook;

import java.util.ArrayList;

public class EmotionList {

    private ArrayList<Emotion> emotionList;

    public EmotionList(){
        emotionList = new ArrayList<Emotion>();

        // add each Emotion object into emotionList
        emotionList.add(new Emotion("Love", "❤"));
        emotionList.add(new Emotion("Joy","\uD83D\uDE02" ));
        emotionList.add(new Emotion("Surprise" ,"\uD83D\uDE2E"));
        emotionList.add(new Emotion("Anger","\uD83D\uDE20"));
        emotionList.add(new Emotion("Sadness","\uD83D\uDE14"));
        emotionList.add(new Emotion("Fear","\uD83D\uDE28"));
    }

    public ArrayList<Emotion> getEmotionList(){
        return emotionList;
    }
}