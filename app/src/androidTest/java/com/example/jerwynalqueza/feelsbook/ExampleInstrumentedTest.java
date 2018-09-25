package com.example.jerwynalqueza.feelsbook;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

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
        Emotion love = new Emotion("Love", "❤");
        assertTrue("Name isn't Correct", "Love".equals(love.getName()));
        assertTrue("Emote isn't Correct", "❤".equals(love.getEmoji()));
        addEntryToList(love);
    }

    public void addEntryToList(Emotion emotion) {

        EmotionList emotionList = new EmotionList();

        assertTrue("Love isn't in EmotionList", emotionList.getEmotionList().get(0).getName().equals("Love"));
        assertTrue("Fear Emoji isn't in EmotionList", emotionList.getEmotionList().get(5).getEmoji().equals("\uD83D\uDE28"));

        String string = "I'm in love";
        Entry newEntry0 = new Entry("Love", "January 2 2019", string, emotionList.getEmotionList());
        Entry newEntry1 = new Entry("Love", "January 3 2019", "I'm in love1", emotionList.getEmotionList());
        Entry newEntry2 = new Entry("Love", "January 4 2019", "I'm in love2", emotionList.getEmotionList());
        newEntry0.addCommentDate();
        newEntry1.addCommentDate();
        newEntry2.addCommentDate();

        assertTrue("Entry count isn't updated", emotionList.getEmotionList().get(0).getCount() == 3);
        newEntry1.deleteCommentDate();
        assertTrue("Entry count isn't updated", emotionList.getEmotionList().get(0).getCount() == 2);
        assertTrue("First comment is inaccurate", emotionList.getEmotionList().get(0).getComments().get(0).equals("I'm in love"));
        assertTrue("Second comment is inaccurate", emotionList.getEmotionList().get(0).getComments().get(1).equals("I'm in love2"));
        assertTrue("commentList size isn't accurate", emotionList.getEmotionList().get(0).getComments().size() == 2);

        newEntry2.editComment("YOOOOO");
        assertTrue("Entry1 comment wasn't changed", emotionList.getEmotionList().get(0).getComments().get(1).equals("YOOOOO"));

    }
}