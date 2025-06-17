package com.example.tilesurvivor.game;

import android.graphics.Canvas;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.ScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Camera;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {

    private static final String TAG = Player.class.getSimpleName();
    private final Player player;

    public ScrollBackground getscrollBackground() {
        return bg;
    }

    private final ScrollBackground bg;
    private float left = 180.0f;
    private float right = 0.0f;
    private float up = 90.0f;
    private float down = 270.0f;

    public enum Layer {
        bg, player, enemy, touch, Weapon, controller;
        public static final int COUNT = values().length;
    }
    public MainScene() {
        initLayers(Layer.COUNT);

        Camera camera = Camera.getInstance();
        camera.setViewSize(Metrics.width, Metrics.height);
        camera.setWorldSize(1920, 1920);

        bg = new ScrollBackground(R.mipmap.tilemap);
        add(Layer.bg, bg);
        add(Layer.controller, new WaveGen(this, 1.0f));

        player = new Player();
        player.setPosition(450, 800, 100, 100);
        add(Layer.player, player);

        add(Layer.touch, new Button(R.mipmap.btn_left, 150f, 1400f, 150f, 75f, pressed -> {
            player.setDirection(Player.Direction.LEFT);
            return false;
        }));
        add(Layer.touch, new Button(R.mipmap.btn_right, 350f, 1400f, 150f, 75f, pressed -> {
            player.setDirection(Player.Direction.RIGHT);
            return false;
        }));
        add(Layer.touch, new Button(R.mipmap.btn_up, 250f, 1300f, 75f, 150f, pressed -> {
            player.setDirection(Player.Direction.UP);
            return false;
        }));
        add(Layer.touch, new Button(R.mipmap.btn_down, 250f, 1500f, 75f, 150f, pressed -> {
            player.setDirection(Player.Direction.DOWN);
            return false;
        }));
        add(Layer.touch, new Button(R.mipmap.btn_move, 550f, 1400f, 150f, 150f, pressed -> {
            player.moveScroll(bg);
            return false;
        }));
        add(Layer.touch, new Button(R.mipmap.btn_attack, 750f, 1400f, 150f, 150f, pressed -> {
            player.fire();
            return false;
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

    @Override
    public boolean onBackPressed() {
        new PauseScene().push();
        return true;
    }
}