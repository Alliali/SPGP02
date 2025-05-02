package com.example.tilesurvivor;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GameView extends View implements Choreographer.FrameCallback {
    public static float frameTime;
    private static long previousNanos;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        scheduleUpdate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (previousNanos != 0) {
            frameTime = (frameTimeNanos - previousNanos) / 1_000_000_000f;
            update();
            invalidate();
        }
        previousNanos = frameTimeNanos;
        if (isShown()) {
            scheduleUpdate();
        }
    }

    private void scheduleUpdate() {
        Choreographer.getInstance().postFrameCallback(this);
    }

    private void update() {

    }
}
