package com.example.magicsquare;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.Before;

//import androidx.lifecycle.Lifecycle;
//import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.robolectric.Robolectric;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE, sdk=28)

public class GameUnitTest {
    GameActivity gameActivity;
    TextView res_1l_v;
    TextView res_2l_v;
    TextView res_3l_v;
    TextView res_1c_v;
    TextView res_2c_v;
    TextView res_3c_v;
    Number res_1l;
    Number res_2l;
    Number res_3l;
    Number res_1c;
    Number res_2c;
    Number res_3c;

    @Before
    public void setUp() {
        //ActivityScenario scenario = ActivityScenario.launch(GameActivity.class);
        gameActivity = Robolectric.setupActivity(GameActivity.class);
        res_1l_v = gameActivity.findViewById(R.id.res_l1);
        res_2l_v = gameActivity.findViewById(R.id.res_l2);
        res_3l_v = gameActivity.findViewById(R.id.res_l3);
        res_1c_v = gameActivity.findViewById(R.id.res_c1);
        res_2c_v = gameActivity.findViewById(R.id.res_c2);
        res_3c_v = gameActivity.findViewById(R.id.res_c3);
        res_1l = Integer.valueOf(res_1l_v.getText().toString());
        res_2l = Integer.valueOf(res_2l_v.getText().toString());
        res_3l = Integer.valueOf(res_3l_v.getText().toString());
        res_1c = Integer.valueOf(res_1c_v.getText().toString());
        res_2c = Integer.valueOf(res_2c_v.getText().toString());
        res_3c = Integer.valueOf(res_3c_v.getText().toString());
    }

    @Test
    public void testGenerateAndDisplay() throws Exception {
        int[] arrayList = new int[0];
        arrayList = GameActivity.generate();
        Number sum1l = arrayList[0]+arrayList[1]+arrayList[2];
        Number sum2l = arrayList[3]+arrayList[4]+arrayList[5];
        Number sum3l = arrayList[6]+arrayList[7]+arrayList[8];
        Number sum1c = arrayList[0]+arrayList[3]+arrayList[6];
        Number sum2c = arrayList[1]+arrayList[4]+arrayList[7];
        Number sum3c = arrayList[2]+arrayList[5]+arrayList[8];

        Assert.assertEquals(res_1l, sum1l);
        Assert.assertEquals(res_2l, sum2l);
        Assert.assertEquals(res_3l, sum3l);
        Assert.assertEquals(res_1c, sum1c);
        Assert.assertEquals(res_2c, sum2c);
        Assert.assertEquals(res_3c, sum3c);
    }
}