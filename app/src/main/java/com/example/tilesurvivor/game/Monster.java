package com.example.tilesurvivor.game;

import android.graphics.Rect;

import com.example.tilesurvivor.R;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.ScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.SheetSprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class Monster extends SheetSprite implements IRecyclable {
    private static final float SPEED = 50.0f;
    private static Rect[][] rects_array;

    @Override
    public void onRecycle() {

    }

    public enum Type {
        pawn, king, knight, rook, bishop, queen
    }
    public Monster() {
        super(R.mipmap.monsterpieces, 1.0f);
        if (rects_array == null) {
            int type_count = Type.values().length;
            rects_array = new Rect[type_count][];
            int x = 0;
            int offset = 33;
            for (int i = 0; i < type_count; i++) {
                rects_array[i] = new Rect[1];
                for (int j = 0; j < 1; j++) {
                    rects_array[i][j] = new Rect(x, 0, x + offset, 42);
                    x += offset;
                }
            }
        }

        setPosition(0,0,100,100);
    }

    public static Monster get(Type type) {
        return Scene.top().getRecyclable(Monster.class).init(type);
    }

    public Monster init(Type type) {
        srcRects = rects_array[type.ordinal()];
        dx = dy = 0;
        return this;
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) Scene.top();
        ArrayList<IGameObject> players = scene.objectsAt(MainScene.Layer.player);
        for (IGameObject gameObject : players) {
            Player player = (Player) gameObject;
            float tx = player.getX();
            float ty = player.getY();
            float vx = tx - x;
            float vy = ty - y;
            float length = (float) Math.sqrt(vx * vx + vy * vy);
            if (length != 0) {
                dx = vx / length * SPEED;
                dy = vy / length * SPEED;
            }
            x += dx * GameView.frameTime;
            y += dy * GameView.frameTime;
        }

        ScrollBackground scrollBackground = scene.getscrollBackground();
        float scrollDx = scrollBackground.getDeltaX();
        float scrollDy = scrollBackground.getDeltaY();
        x -= scrollDx;
        y -= scrollDy;

        setPosition(x, y);
    }
}