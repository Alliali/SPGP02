package com.example.tilesurvivor.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class WaveGen implements IGameObject {
    private final MainScene scene;
    private final float interval;
    private float time;
    private static final Random rand = new Random();

    public WaveGen(MainScene scene, float interval) {
        this.scene = scene;
        this.interval = interval;
    }


    @Override
    public void update() {
        time += GameView.frameTime;
        if (time >= interval) {
            spawn();
            time -= interval;
        }
    }

    private void spawn() {
        float x = rand.nextFloat() * Metrics.width;
        float y = rand.nextFloat() * Metrics.height;
        Monster.Type[] types = Monster.Type.values();
        Monster.Type type = types[rand.nextInt(types.length)];
        Monster monster = Monster.get(type);
        monster.setPosition(0, y);
        scene.add(MainScene.Layer.enemy, monster);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
