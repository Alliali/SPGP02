package com.example.tilesurvivor.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Camera;

public class MainScene extends Scene {

    private final Player player;
    private final Camera camera = new Camera();

    public enum Layer {
        bg, player, controller;
        public static final int COUNT = values().length;
    }
    public MainScene() {
        initLayers(Layer.COUNT);

        add(Layer.bg, new HorzScrollBackground(R.mipmap.tilemap, 0));

        player = new Player();
        add(Layer.player, player);
        //add(Layer.controller, new MapLoader(this));

        camera.setCenter(player.getX(), player.getY());

    }

//    @Override
//    public void update() {
//        super.update();
//
//    }

//    @Override
//    public void draw(Canvas canvas) {
//        canvas.save();
//        camera.apply(canvas);
//        super.draw(canvas);
//        canvas.restore();
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return player.onTouch(event);
    }
}
