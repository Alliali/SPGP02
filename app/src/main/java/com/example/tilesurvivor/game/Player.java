package com.example.tilesurvivor.game;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.SheetSprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.RectUtil;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Camera;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Player extends SheetSprite {
    private static final String TAG = Player.class.getSimpleName();

    public enum State {
        idle, move, attack
    }
    protected State state = State.idle;
    protected static Rect[][] srcRectsArray = {
            new Rect[] {
                    new Rect(0, 2, 33, 42)  // State.idle
            },
            new Rect[] {
                    new Rect(0, 43, 33, 83) // State.move
            }
    };
    public Player() {
        super(R.mipmap.chesspieces2, 8);
//        x = 1000.0f;
//        y = 1000.0f;
        setPosition(x , y, 100, 100);
        Log.d(TAG, "이게 X = " + x + " 이게 Y = " + y);
        srcRects = srcRectsArray[state.ordinal()];
    }

    private void setState(State state) {
        this.state = state;
    }
    public void move() {
        if (state == State.idle) {
            //x += 30f;
            setState(State.move);
        } else {
            setState(State.idle);
        }
        //setPosition(x, y, 100, 100);
        srcRects = srcRectsArray[state.ordinal()];
    }

    @Override
    public void update() {
        RectUtil.setRect(dstRect, x, y, width, height);
    }
    public boolean onTouch(float targetX, float targetY) {
        //if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //move();
            x = targetX;
            y = targetY;
        //}
        return true;
    }
}