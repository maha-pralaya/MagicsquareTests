package com.example.magicsquare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView res_l1,res_l2,res_l3,res_c1,res_c2,res_c3;
    EditText[] res = new EditText[9];

    Chronometer timer;

    static final String EXTRA_MESSAGE = "GameActivity";
    int[] magicsquare;
    float help;
    boolean init = true;
    boolean[] helps = {false,false,false,false,false,false,false,false,false};

    private final int STATE_WIN = 2;
    private final int STATE_SUBMITED = 1;
    private final int STATE_RUNNING = 0;

    private int STATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intentRecu = getIntent();
        help = intentRecu.getFloatExtra("HELP", 1);

        // find textedits
        res[0] = findViewById(R.id.res_0);
        res[1] = findViewById(R.id.res_1);
        res[2] = findViewById(R.id.res_2);
        res[3] = findViewById(R.id.res_3);
        res[4] = findViewById(R.id.res_4);
        res[5] = findViewById(R.id.res_5);
        res[6] = findViewById(R.id.res_6);
        res[7] = findViewById(R.id.res_7);
        res[8] = findViewById(R.id.res_8);

        // find column results textviews
        res_c1 = findViewById(R.id.res_c1);
        res_c2 = findViewById(R.id.res_c2);
        res_c3 = findViewById(R.id.res_c3);
        // find line results textviews
        res_l1 = findViewById(R.id.res_l1);
        res_l2 = findViewById(R.id.res_l2);
        res_l3 = findViewById(R.id.res_l3);

        timer = findViewById(R.id.timer);

        magicsquare = generate();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // set help
        for (int i = 0; i < res.length; i++) {
            if((init && Math.random() <= help)/* || helps[0]*/) { res[i].setText("" + magicsquare[i]); res[i].setFocusable(false); helps[i] = true;}
        }

        // set results texts
        res_c1.setText("" + (magicsquare[0] + magicsquare[3] + magicsquare[6]));
        res_c2.setText("" + (magicsquare[1] + magicsquare[4] + magicsquare[7]));
        res_c3.setText("" + (magicsquare[2] + magicsquare[5] + magicsquare[8]));
        res_l1.setText("" + (magicsquare[0] + magicsquare[1] + magicsquare[2]));
        res_l2.setText("" + (magicsquare[3] + magicsquare[4] + magicsquare[5]));
        res_l3.setText("" + (magicsquare[6] + magicsquare[7] + magicsquare[8]));

        ((Button) findViewById(R.id.resume)).setClickable(false);
        ((Button) findViewById(R.id.newgame)).setClickable(false);




        switch(STATE) {
            case STATE_WIN:
                ((Button) findViewById(R.id.resume)).setClickable(false);
                ((Button) findViewById(R.id.submit)).setClickable(false);
                ((Button) findViewById(R.id.newgame)).setClickable(true);
                break;
            case STATE_RUNNING :
                ((Button) findViewById(R.id.resume)).setClickable(false);
                ((Button) findViewById(R.id.submit)).setClickable(true);
                ((Button) findViewById(R.id.newgame)).setClickable(false);
                timer.start();
                break;
            case STATE_SUBMITED:
                ((Button) findViewById(R.id.resume)).setClickable(true);
                ((Button) findViewById(R.id.submit)).setClickable(false);
                ((Button) findViewById(R.id.newgame)).setClickable(true);
                timer.start();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntArray("magicsquare", magicsquare);
        outState.putBooleanArray("helps", helps);
        outState.putBoolean("init", false);
        outState.putLong("timer", timer.getBase());
        outState.putInt("state", STATE);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        magicsquare = savedInstanceState.getIntArray("magicsquare");
        helps = savedInstanceState.getBooleanArray("helps");
        init = savedInstanceState.getBoolean("init");
        timer.setBase(savedInstanceState.getLong("timer"));
        STATE = savedInstanceState.getInt("state");
    }

    public static int[] generate() {
        Random rand = new Random();
        int random = rand.nextInt(9);
        int[] magicsquare = {0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < 9; i++) {
            while (magicsquare[random] != 0) random = rand.nextInt(9);
            magicsquare[random] = i + 1;
        }

        return magicsquare;
    }


    public void submit(View view) {
        String toastString = "Correct";
        int[] user_ans = new int[9];
        boolean[] results = new boolean[9];
        for (int i = 0; i < res.length; i++) {
            user_ans[i] = Integer.parseInt(res[i].getText().toString());
            results[i] = res[i].getText().toString().equals(String.valueOf(magicsquare[i]));

            res[i].setFocusable(false);

            if(!results[i]) res[i].setTextColor(Color.RED);
        }

        if(results[0] && results[1] && results[2]
            && results[3] && results[4] && results[5]
            && results[6] && results[7] && results[8]) {
            STATE = STATE_WIN;
            Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();
            timer.stop();
        } else {
            STATE = STATE_SUBMITED;

            // check 0
            if(Arrays.binarySearch(user_ans, 0) >= 0)
                Toast.makeText(this, "Answers must be numbers between 1 and 9", Toast.LENGTH_SHORT).show();

            // check one of each number
            int[] a = user_ans.clone();
            Arrays.sort(a);
            if(Arrays.binarySearch(a, 1) < 0 || Arrays.binarySearch(a, 2) < 0 ||
               Arrays.binarySearch(a, 3) < 0 || Arrays.binarySearch(a, 4) < 0 ||
               Arrays.binarySearch(a, 5) < 0 || Arrays.binarySearch(a, 6) < 0 ||
               Arrays.binarySearch(a, 7) < 0 || Arrays.binarySearch(a, 8) < 0 ||
               Arrays.binarySearch(a, 9) < 0)
                Toast.makeText(this, "You have to enter each numbers between 1 and 9 once", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Red numbers are wrongly placed", Toast.LENGTH_SHORT).show();


            // enable Resume btn
            ((Button) findViewById(R.id.resume)).setClickable(true);
        }

        // disable Submit btn & enable NewGame btn
        ((Button) findViewById(R.id.submit)).setClickable(false);
        ((Button) findViewById(R.id.newgame)).setClickable(true);
    }

    public void resume(View view) {
        STATE = STATE_RUNNING;
        for (int i = 0; i < res.length; i++) {
            boolean isCorrect = res[i].getText().toString().equals(String.valueOf(magicsquare[i]));

            res[i].setFocusable(false);

            if(!isCorrect) {res[i].setFocusableInTouchMode(true); res[i].setFocusable(true);}
        }

        // enable Submit btn
        ((Button) findViewById(R.id.submit)).setClickable(true);
        ((Button) findViewById(R.id.resume)).setClickable(false);
    }

    public void newgame(View view) {
        Intent monIntent = new Intent(this, GameActivity.class);
        monIntent.putExtra("HELP",help);
        startActivity(monIntent);
        this.finish();
    }

    public void exit(View view) {
        this.finish();
    }

    public double Exp(double lambda) {
        Random rand = new Random();
        return -(1 / lambda) * Math.log( 1 - rand.nextDouble() );
    }

    public void help(View view) {
        boolean ok = false;
        for (int i = 0; i < helps.length; i++) if (!helps[i]) ok = true;

        if(ok) {
            Random rand = new Random();
            int random = rand.nextInt(9);
            while (helps[random]) random = rand.nextInt(9);
            helps[random] = true;

            res[random].setText("" + magicsquare[random]);
            res[random].setFocusable(false);
        }
    }
}
