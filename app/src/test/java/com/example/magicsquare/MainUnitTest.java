package com.example.magicsquare;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import android.content.ComponentName;
import android.content.Intent;
import android.widget.Button;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE,sdk=28)

public class MainUnitTest {
    MainActivity mainActivity;
    Button btnNewGame;
    ShadowActivity shadowActivity;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        btnNewGame = mainActivity.findViewById(R.id.buttonNew);
    }

    @Test
    public void testActivityStart() throws Exception {
        btnNewGame.performClick();

        Intent intent = shadowActivity.peekNextStartedActivityForResult().intent;
        assertEquals(intent.getStringExtra(GameActivity.EXTRA_MESSAGE),"GameActivity");
        assertEquals(intent.getComponent(), new ComponentName(mainActivity, GameActivity.class));
    }
}