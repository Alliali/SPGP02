package com.example.tilesurvivor.game;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Weapon extends Sprite implements IRecyclable {
    private static final float RADIUS = 30f;
    private static final float SPEED = 800f;

    public Weapon() {
        super(R.mipmap.weapon_rook, 0, 0, RADIUS, RADIUS);
    }

    public static Weapon get(float x, float y, Player.Direction direction) {
        return Scene.top().getRecyclable(Weapon.class).init(x, y, direction);
    }

    private Weapon init(float x, float y, Player.Direction direction) {
        switch (direction) {
            case LEFT:  dx = -SPEED; dy = 0; break;
            case RIGHT: dx = SPEED; dy = 0; break;
            case UP:    dx = 0; dy = -SPEED; break;
            case DOWN:  dx = 0; dy = SPEED; break;
        }
        setPosition(x, y, 64, 128f);
        return this;
    }

    @Override
    public void update() {
        super.update();
        x += dx * GameView.frameTime;
        y += dy * GameView.frameTime;
        if (x < -RADIUS || x > Metrics.width + RADIUS || y < -RADIUS || y > Metrics.height + RADIUS) {
            Scene.top().remove(MainScene.Layer.Weapon, this);
        }
    }

    @Override
    public void onRecycle() {

    }
}
