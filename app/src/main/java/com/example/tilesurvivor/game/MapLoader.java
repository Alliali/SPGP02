package com.example.tilesurvivor.game;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class MapLoader implements IGameObject {

    private static final String TAG = MapLoader.class.getSimpleName();
    private final MainScene scene;

    public MapLoader(MainScene mainScene) {
        this.scene = mainScene;
        loadStage(GameView.view.getContext(), 1);
    }

    private void loadStage(Context context, int stage) {
        AssetManager assetManager = context.getAssets();
        try {
            String file = String.format("tilemap.tmj", stage);
            InputStream inputStream = assetManager.open(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            JsonReader jsonReader = new JsonReader(inputStreamReader);
            Log.d(TAG, "YES");
        } catch (IOException ioException) {
            Log.d(TAG, "NO");
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
