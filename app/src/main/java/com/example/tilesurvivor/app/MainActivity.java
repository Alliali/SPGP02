package com.example.tilesurvivor.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tilesurvivor.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnStartGame(View view) {
        startGame(1);
    }

    private void startGame(int stage) {
        Intent intent = new Intent(this, TileSurvivorActivity.class);
        startActivity(intent);
    }
}