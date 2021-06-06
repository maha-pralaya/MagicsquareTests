package com.example.magicsquare;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final float NEW_GAME_NUMBER = 5/9f;
    public static final float ADVANCED_GAME_NUMBER = 3/9f;
    public static final float EXPERT_GAME_NUMBER = 0/9f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showrules(View view) {
        Intent implicitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Magic_square"));
        startActivity(implicitIntent);
    }

    public void newgame(View view) {
        Intent monIntent = new Intent(this, GameActivity.class);
        monIntent.putExtra("HELP",NEW_GAME_NUMBER);
        startActivity(monIntent);
    }

    public void advancedgame(View view) {
        Intent monIntent = new Intent(this, GameActivity.class);
        monIntent.putExtra("HELP",ADVANCED_GAME_NUMBER);
        startActivity(monIntent);
    }

    public void expertgame(View view) {
        Intent monIntent = new Intent(this, GameActivity.class);
        monIntent.putExtra("HELP",EXPERT_GAME_NUMBER);
        startActivity(monIntent);
    }
}
