package com.example.tilesurvivor.app;

import android.os.Bundle;

import com.example.tilesurvivor.BuildConfig;
import com.example.tilesurvivor.game.MainScene;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class TileSurvivorActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        Metrics.setGameSize(900, 1600);
        super.onCreate(savedInstanceState);

        new MainScene().push();
    }
}