package kr.ac.tukorea.ge.spgp2025.a2dg.framework.view;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;

public class Camera {
    private final RectF worldRect = new RectF();
    private static final Matrix matrix = new Matrix();
    private final Matrix inverseMatrix = new Matrix();
    private final float[] temp = new float[2];
    private float viewWidth, viewHeight;
    private float scale = 1.0f;

    private static final Camera instance = new Camera();
    public static Camera getInstance() {
        return instance;
    }

    private Camera() {}

    public void setViewSize(float viewWidth, float viewHeight) {
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
    }

    public void setWorldSize(float width, float height) {
        worldRect.set(0, 0, width, height);
    }

    public void lookAt(float targetX, float targetY) {
        float left = targetX - viewWidth / 2 /scale;
        float top = targetY - viewHeight / 2 /scale;

//        left = Math.max(worldRect.left, Math.min(left, worldRect.right - viewWidth / scale));
//        top = Math.max(worldRect.top, Math.min(top, worldRect.bottom - viewHeight / scale));

        matrix.reset();
        matrix.postScale(scale, scale);
        matrix.postTranslate(-left * scale, -top * scale);
        matrix.invert(inverseMatrix);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    public static void apply(Canvas canvas) {
        canvas.concat(matrix);
    }
    public float[] screenToWorld(float x, float y) {
        temp[0] = x;
        temp[1] = y;
        inverseMatrix.mapPoints(temp);
        return temp;
    }
}