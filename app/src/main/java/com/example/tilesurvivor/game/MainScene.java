package com.example.tilesurvivor.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Camera;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {

    private static final String TAG = Player.class.getSimpleName();
    private final Player player;
    private final Sprite background;

    public enum Layer {
        bg, player, ui, controller;
        public static final int COUNT = values().length;
    }
    public MainScene() {
        initLayers(Layer.COUNT);

        Camera camera = Camera.getInstance();
        camera.setViewSize(Metrics.width, Metrics.height);
        camera.setWorldSize(1920, 1920);
        camera.setScale(2.0f);

        background = new Sprite(R.mipmap.tilemap);
        background.setPosition(1920 / 2f, 1920 / 2f, 1920, 1920);
        add(Layer.bg, background);

        player = new Player();
        player.setPosition(960, 960, 100, 100);
        add(Layer.player, player);

        add(Layer.ui, new Sprite(R.mipmap.btn_left, 200f, 1400f, 150f, 75f));
        add(Layer.ui, new Sprite(R.mipmap.btn_right, 400f, 1400f, 150f, 75f));
        add(Layer.ui, new Sprite(R.mipmap.btn_up, 300f, 1300f, 75f, 150f));
        add(Layer.ui, new Sprite(R.mipmap.btn_down, 300f, 1500f, 75f, 150f));

        //add(Layer.controller, new MapLoader(this));
    }

    @Override
    public void update() {
        super.update();
        Camera.getInstance().lookAt(player.getX(), player.getY());
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Camera.apply(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float[] world = Camera.getInstance().screenToWorld(event.getX(), event.getY());
        player.onTouch(world[0], world[1]);
        return true;
    }
}
