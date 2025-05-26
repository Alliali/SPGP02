package kr.ac.tukorea.ge.spgp2025.a2dg.framework.view;

import android.graphics.Canvas;
import android.graphics.Matrix;

public class Camera {
    private static final Matrix matrix = new Matrix();
    private static final Matrix inverse = new Matrix();
    private static float centerX, centerY;

    public static void setCenter(float x, float y) {
        centerX = x;
        centerY = y;
        updateMatrix();
    }

    public static void updateMatrix() {
        matrix.reset();
        matrix.postTranslate(-centerX + Metrics.width / 2, -centerY + Metrics.height / 2);
        matrix.invert(inverse);
    }

    public static float getCenterX() {
        return centerX;
    }

    public static float getCenterY() {
        return centerY;
    }

    public static void apply(Canvas canvas) {
        canvas.concat(matrix);
    }
}
