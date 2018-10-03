package com.example.jerwynalqueza.feelsbook;

import java.util.ArrayList;
import java.util.Date;

public class EntryList {

    // Class that holds an ArrayList of entries and modifies entries

    private ArrayList<Entry> entryList;

    public EntryList(){
        entryList = new ArrayList<Entry>();
    }

    public ArrayList<Entry> getEntryList(){
        return entryList;
    }
    public void setEntryList(ArrayList<Entry> entryList){
        this.entryList = entryList;
    }

    public void addEntryToList(Entry entry){ entryList.add(entry); }
    public void deleteEntryFromList(Entry entry){ entryList.remove(entry); }
}