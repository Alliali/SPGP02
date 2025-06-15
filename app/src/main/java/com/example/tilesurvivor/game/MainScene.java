package com.example.tilesurvivor.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.ScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Camera;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {

    private static final String TAG = Player.class.getSimpleName();
    private final Player player;
    private final ScrollBackground bg;

    public enum Layer {
        bg, player, ui, touch, Weapon, controller;
        public static final int COUNT = values().length;
    }
    public MainScene() {
        initLayers(Layer.COUNT);

        Camera camera = Camera.getInstance();
        camera.setViewSize(Metrics.width, Metrics.height);
        camera.setWorldSize(1920, 1920);

        bg = new ScrollBackground(R.mipmap.tilemap);
        add(Layer.bg, bg);


        player = new Player();
        player.setPosition(450, 800, 100, 100);
        add(Layer.player, player);

        add(Layer.touch, new Button(R.mipmap.btn_left, 150f, 1400f, 150f, 75f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                bg.direction(180.0f, true);
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.btn_right, 350f, 1400f, 150f, 75f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                bg.direction(0.0f, true);
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.btn_up, 250f, 1300f, 75f, 150f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                bg.direction(90.0f, true);
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.btn_down, 250f, 1500f, 75f, 150f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                bg.direction(270.0f, true);
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.chesspieces, 500f, 1400f, 150f, 75f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                bg.checkMove(true);
                return false;
            }
        }));

        //add(Layer.controller, new MapLoader(this));
    }


    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
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

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float[] world = Camera.getInstance().screenToWorld(event.getX(), event.getY());
//        player.onTouch(world[0], world[1]);
//        return true;
//    }
}
