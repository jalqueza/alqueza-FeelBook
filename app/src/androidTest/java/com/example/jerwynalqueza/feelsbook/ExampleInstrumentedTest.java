package com.example.jerwynalqueza.feelsbook;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.jerwynalqueza.feelsbook", appContext.getPackageName());
        testEmotion();
    }

    public void testEmotion() {
        Emotion love = new Emotion("Love");
        assertTrue("Name isn't Correct", "Love".equals(love.getName()));
        addEntryToList(love);
    }

    public void addEntryToList(Emotion emotion) {

        EmotionList emotionList = new EmotionList();
        assertTrue("Love isn't in EmotionList", emotionList.getEmotionList().get(0).getName().equals("Love"));

        EntryList entryList = new EntryList();
        Entry entry = new Entry("Love", new Date(),"In love");
        entryList.addEntryToList(entry);
        assertTrue("Comment isn't correct", entryList.getEntryList().get(0).getComment().equals("In love"));
    }
}