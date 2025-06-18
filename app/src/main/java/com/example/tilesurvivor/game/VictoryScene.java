package com.example.tilesurvivor.game;

import com.example.tilesurvivor.R;
import com.example.tilesurvivor.app.MainActivity;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class VictoryScene extends Scene {
    enum VictoryLayer {
        bg, ui
    }

    public VictoryScene() {
        initLayers(VictoryLayer.values().length);
        Sprite bg = new Sprite(R.mipmap.trans_50b);
        float w = Metrics.width, h = Metrics.height;
        bg.setPosition(w / 2, h / 2, w, h);
        add(VictoryLayer.bg, bg);

        float btn_y1 = h / 2 - 120;
        float btn_y2 = h / 2 + 120;
        add(PauseScene.PauseLayer.ui, new Button(R.mipmap.btn_restart, w / 2, btn_y1, 512, 192, pressed ->
        {
            add(MainScene.Layer.controller, new WaveGen(new MainScene(), 1.0f));
            return false;
        }));
        add(PauseScene.PauseLayer.ui, new Button(R.mipmap.btn_exit, w / 2, btn_y2, 512, 192, pressed ->
        {
            Scene.popAll();
            return true;
        }));
    }

    // Overridables
    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    protected int getTouchLayerIndex() {
        return PauseScene.PauseLayer.ui.ordinal();
    }
}
