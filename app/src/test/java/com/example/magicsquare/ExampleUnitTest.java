package com.example.magicsquare;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.Before;

import org.robolectric.Robolectric;
import android.widget.Button;
import android.widget.TextView;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)

public class ExampleUnitTest {
    GameActivity gameActivity;
    TextView res_1l;
    TextView res_2l;
    TextView res_3l;
    TextView res_1c;
    TextView res_2c;
    TextView res_3c;
    Button btnHelp;

    @Before
    public void setUp() {
        gameActivity = Robolectric.setupActivity(GameActivity.class);
        res_1l = (TextView)gameActivity.findViewById(R.id.res_l1);
        res_2l = (TextView)gameActivity.findViewById(R.id.res_l2);
        res_3l = (TextView)gameActivity.findViewById(R.id.res_l2);
        res_1c = (TextView)gameActivity.findViewById(R.id.res_c1);
        res_2c = (TextView)gameActivity.findViewById(R.id.res_c2);
        res_3c = (TextView)gameActivity.findViewById(R.id.res_c2);
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