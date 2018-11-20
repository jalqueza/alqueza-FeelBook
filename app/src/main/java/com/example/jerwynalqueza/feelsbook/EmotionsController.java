package com.example.jerwynalqueza.feelsbook;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class EmotionsController {

    // Controller that holds entryList, emotionList and emotionNameList and helps manage entry add and deletion
    // also sorts entries by date

    private static EntryList entryListObj = new EntryList();
    private static EmotionList emotionListObj = new EmotionList();
    private static ArrayList<String> emotionNameList = null;


    static public void loadFile(ArrayList<Emotion> emotionList, ArrayList<Entry> entryList){
        EmotionsController.emotionListObj.setEmotionList(emotionList);
        EmotionsController.entryListObj.setEntryList(entryList);
    }


    // GETTERS
    static public EntryList getEntryListObj(){ return entryListObj; }

    static public EmotionList getEmotionListObj(){ return emotionListObj; }

    static public ArrayList<String> getNameList(){
        // returns list of each emotions name

        if (emotionNameList == null) {
            emotionNameList = new ArrayList<String>();
            for (int i = 0; i < emotionListObj.getEmotionList().size(); i++)
                emotionNameList.add(emotionListObj.getEmotionList().get(i).getName());
        }
        return emotionNameList;
    }

    static public ArrayList<Integer> getCountList() {
        // returns list each emotions count

        ArrayList<Integer> countList = new ArrayList<Integer>();
        for (int i = 0; i < emotionListObj.getEmotionList().size(); i++) {
            countList.add(emotionListObj.getEmotionList().get(i).getCount());
        }
        return countList;
    }

    // Actions to add/delete entries made easier with controller
    static public void addEntry(String emotion, Date date, String comment){
        Entry entry = new Entry(emotion, date, comment);
        entryListObj.addEntryToList(entry);
        entry.increaseCount(emotionListObj.getEmotionList());
    }
    static public void deleteEntry(Entry entry){
        entryListObj.deleteEntryFromList(entry);
        entry.decreaseCount(emotionListObj.getEmotionList());}

    // Sorts Entry List From Ascending Order
    static public void sort(){
        Collections.sort(entryListObj.getEntryList(), new Comparator<Entry>() {
            public int compare(Entry e1, Entry e2) {
                Long date1 = e1.getDate().getTime();
                Long date2 = e2.getDate().getTime();
                return date2.compareTo(date1);
            }
        });
    }



}