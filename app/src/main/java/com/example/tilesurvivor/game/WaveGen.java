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
        float x = 0, y = 0;
        int edge = rand.nextInt(4);
        switch (edge) {
            case 0: // 왼쪽
                x = -100;
                y = rand.nextFloat() * Metrics.height;
                break;
            case 1: // 오른쪽
                x = Metrics.width + 100;
                y = rand.nextFloat() * Metrics.height;
                break;
            case 2: // 위쪽
                x = rand.nextFloat() * Metrics.width;
                y = -100;
                break;
            case 3: // 아래쪽
                x = rand.nextFloat() * Metrics.width;
                y = Metrics.height + 100;
                break;
        }
        Monster.Type[] types = Monster.Type.values();
        Monster.Type type = types[rand.nextInt(types.length)];
        Monster monster = Monster.get(type);
        monster.setPosition(x, y);
        scene.add(MainScene.Layer.enemy, monster);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
