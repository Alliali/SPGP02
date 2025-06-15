package com.example.tilesurvivor.game;

import android.graphics.Rect;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;

public class Weapon extends Sprite {

    private static final float BULLET_WIDTH = 68f;
    private static final float BULLET_HEIGHT = BULLET_WIDTH * 40 / 28;
    private static final float SPEED = 2000f;

    public Weapon() {
        super(R.mipmap.btn_action);
        srcRect = new Rect();
    }
}
