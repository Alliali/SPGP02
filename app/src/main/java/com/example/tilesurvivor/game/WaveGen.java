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
        float leftLimit = scene.getscrollBackground().getLeftLimit();
        float rightLimit = scene.getscrollBackground().getRightLimit();
        float topLimit = scene.getscrollBackground().getTopLimit();
        float bottomLimit = scene.getscrollBackground().getBottomLimit();
        float scrollDx = scene.getscrollBackground().getTargetX();
        float scrollDy = scene.getscrollBackground().getTargetY();

        float spawnX, spawnY;
        Random random = new Random();
        int edgePos = random.nextInt(4);
        switch (edgePos) {
            case 0: //상
                if (scrollDx < 410) {
                    spawnX = Metrics.width / 2 - (leftLimit - scrollDx);
                } else {
                    spawnX = Metrics.width / 2 - (rightLimit - scrollDx);
                }
                spawnY = Metrics.height / 2 + (topLimit - scrollDy); break;
            case 1: //하
                if (scrollDx < 410) {
                    spawnX = Metrics.width / 2 - (leftLimit - scrollDx);
                } else {
                    spawnX = Metrics.width / 2 - (rightLimit - scrollDx);
                }
                spawnY = Metrics.height / 2 + (bottomLimit - scrollDy); break;
            case 2: //좌
                spawnX = Metrics.width / 2 + (leftLimit - scrollDx);
                if (scrollDy < 170) {
                    spawnY = Metrics.height / 2 - (topLimit - scrollDy);
                    break;
                } else {
                    spawnY = Metrics.height / 2 - (bottomLimit - scrollDy);
                    break;
                }
            case 3: //우
                spawnX = Metrics.width / 2 + (rightLimit - scrollDx);
                if (scrollDy < 170) {
                    spawnY = Metrics.height / 2 - (topLimit - scrollDy);
                    break;
                } else {
                    spawnY = Metrics.height / 2 - (bottomLimit - scrollDy);
                    break;
                }
            default: spawnX = spawnY = 0; break;
        }
        Monster.Type[] types = Monster.Type.values();
        Monster.Type type = types[rand.nextInt(types.length)];
        Monster monster = Monster.get(type);
        monster.setPosition(spawnX, spawnY);
        scene.add(MainScene.Layer.enemy, monster);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
