package com.example.jerwynalqueza.feelsbook;

import android.util.Log;

import java.util.ArrayList;

public class Emotion {

    private String name;
    private int count = 0;


    public Emotion(String name){
        this.name = name;
    }

    public String getName(){ return name; }
    public int getCount(){
        return count;
    }

    public void increaseCount(){
        count++;
    }
    public void decreaseCount(){
        count--;
    }
}