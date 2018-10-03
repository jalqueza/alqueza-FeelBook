package com.example.jerwynalqueza.feelsbook;

import java.util.ArrayList;
import java.util.Date;

public class EntryList {

    public ArrayList<Entry> entryList;

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
    public void editComment(Entry entry, String newComment){
        entry.setComment(newComment);
    }
    public void editDate(Entry entry, Date newDate){
        entry.setDate(newDate);
    }

}