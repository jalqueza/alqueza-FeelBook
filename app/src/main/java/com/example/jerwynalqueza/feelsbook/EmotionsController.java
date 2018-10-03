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


    // Controller that holds entryList, emotionList and emotionNameList

    private static EntryList entryList = new EntryList();
    private static ArrayList<Emotion> emotionList = new EmotionList().getEmotionList();
    private static ArrayList<String> emotionNameList = null;


    static public void loadFile(ArrayList<Emotion> emotionList, ArrayList<Entry> entryList){
        EmotionsController.emotionList = emotionList;
        EmotionsController.entryList.setEntryList(entryList);
    }


    // GETTERS

    static public ArrayList<Entry> getEntryList(){ return entryList.getEntryList(); }

    static public ArrayList<Emotion> getEmotionList(){ return emotionList; }

    static public ArrayList<String> getNameList(){
        // returns list of each emotions name
        if (emotionNameList == null) {
            emotionNameList = new ArrayList<String>();
            for (int i = 0; i < emotionList.size(); i++)
                emotionNameList.add(emotionList.get(i).getName());
        }
        return emotionNameList;
    }
    static public ArrayList<Integer> getCountList() {
        // returns list each emotions count
        ArrayList<Integer> countList = new ArrayList<Integer>();
        for (int i = 0; i < emotionList.size(); i++) {
            countList.add(emotionList.get(i).getCount());
        }
        return countList;
    }

    // Actions to modify entries
    static public void addEntry(String emotion, Date date, String comment){
        Entry entry = new Entry(emotion, date, comment);
        entryList.addEntryToList(entry);
        entry.increaseCount(emotionList);
    }
    static public void deleteEntry(Entry entry){
        entryList.deleteEntryFromList(entry);
        entry.decreaseCount(emotionList);}
    static public void editComment(Entry entry, String newComment){ entryList.editComment(entry,newComment); }
    static public void editDate(Entry entry, Date newDate){ entryList.editDate(entry, newDate); }

    // Sorts Entry List From Ascending Order
    static public void sort(){
        Collections.sort(entryList.entryList, new Comparator<Entry>() {
            public int compare(Entry e1, Entry e2) {
                Long date1 = e1.getDate().getTime();
                Long date2 = e2.getDate().getTime();
                return date2.compareTo(date1);
            }
        });
    }

}