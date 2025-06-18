package com.example.tilesurvivor.game;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.ScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.SheetSprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.RectUtil;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class Player extends SheetSprite implements IBoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private static final float FIRE_INTERVAL = 0.3f;
    private float fireTime = 0f;


    public enum State {idle, move, attack}
    public enum Direction {LEFT, RIGHT, UP, DOWN}
    private RectF collisionRect = new RectF();
    private Direction direction = Direction.RIGHT;
    protected State state = State.idle;
    protected static Rect[][] srcRectsArray = {
            new Rect[] {
                    new Rect(0, 2, 33, 42)  // State.idle
            },
            new Rect[] {
                    new Rect(0, 43, 33, 83) // State.move
            }
    };
    protected static float[][] edgeInsetRatios = {
            {0.2f, 0.25f, 0.2f, 0.0f}
    };
    public Player() {
        super(R.mipmap.chesspieces2, 8);
        setPosition(x , y, 100, 100);
        Log.d(TAG, "이게 X = " + x + " 이게 Y = " + y);
        srcRects = srcRectsArray[state.ordinal()];
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
    private void setState(State state) {
        this.state = state;
        updateCollisionRect();
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
    public void moveScroll(ScrollBackground scrollBackground) {
        switch (direction) {
            case LEFT: scrollBackground.move(-64, 0); break;
            case RIGHT: scrollBackground.move(64, 0); break;
            case UP: scrollBackground.move(0, -64); break;
            case DOWN: scrollBackground.move(0, 64); break;
        }
/*
        if (state == State.idle) {
            //x += 30f;
            setState(State.move);
        } else {
            setState(State.idle);
        }
setPosition(x, y, 100, 100);
srcRects = srcRectsArray[state.ordinal()];
*/
    }

    @Override
    public void update() {
        super.update();
        fireTime += GameView.frameTime;
        RectUtil.setRect(dstRect, x, y, width, height);
        updateCollisionRect();
    }

    public void fire() {
        if (fireTime < FIRE_INTERVAL) return;
        fireTime = 0;
        Weapon weapon = Weapon.get(getX(), getY(), direction);
        Scene.top().add(MainScene.Layer.weapon, weapon);
    }

    private void updateCollisionRect() {
        float[] insets = edgeInsetRatios[state.ordinal()];
        collisionRect.set(
                dstRect.left + width * insets[0],
                dstRect.top + height * insets[1],
                dstRect.right - width * insets[2],
                dstRect.bottom - height * insets[3]
        );
    }
}