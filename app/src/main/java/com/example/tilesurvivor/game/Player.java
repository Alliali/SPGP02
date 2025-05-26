package com.example.tilesurvivor.game;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.SheetSprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Camera;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Player extends SheetSprite {

    private float worldX, worldY;

    public float getX() { return x; }
    public float getY() { return y; }

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
        this.x = 1000f;
        this.y = 1000f;
        setPosition(Metrics.width / 2 , Metrics.height / 2, 200, 200);
        srcRects = srcRectsArray[state.ordinal()];
    }

    private void setState(State state) {
        this.state = state;
    }
    public void move() {
        if (state == State.idle) {
            Camera.setCenter(Camera.getCenterX() + 10, Camera.getCenterY());
            setState(State.move);
        } else {
            setState(State.idle);
        }
        srcRects = srcRectsArray[state.ordinal()];
    }
    public boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            move();
        }
        return false;
    }
}