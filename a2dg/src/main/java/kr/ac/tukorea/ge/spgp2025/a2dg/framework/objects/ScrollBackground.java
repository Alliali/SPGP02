package kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class ScrollBackground extends Sprite {
    private final Rect srcRect = new Rect();    // 원본 이미지에서 자를 부분
    private final RectF dstRect = new RectF();  // 화면에 그릴 위치
    private float deltaX, deltaY;


    private final float leftLimit = -358;
    private final float rightLimit = 1370;
    private final float topLimit = -726;
    private final float bottomLimit = 1002;
    private float scrollX = 410, scrollY = 170;
    private float targetX = 410, targetY = 170;
    private final float scrollSpeed = 300.0f;
    public float getLeftLimit() {
        return leftLimit;
    }
    public float getRightLimit() {
        return rightLimit;
    }
    public float getTopLimit() {
        return topLimit;
    }
    public float getBottomLimit() {
        return bottomLimit;
    }
    public float getTargetX() {
        return targetX;
    }
    public float getTargetY() {
        return targetY;
    }

    public ScrollBackground(int bitmapResId) {
        super(bitmapResId);
        setPosition(bitmap.getWidth() / 2f, bitmap.getHeight() / 2f, bitmap.getWidth(), bitmap.getHeight());
    }

    public void move(float dx, float dy) {
        targetX += dx;
        targetY += dy;

        if (targetX < leftLimit) targetX = leftLimit;
        if (targetY < topLimit) targetY = topLimit;
        if (targetX > rightLimit) targetX = rightLimit;
        if (targetY > bottomLimit) targetY = bottomLimit;
    }

    public float getDeltaX() {
        return deltaX;
    }
    public float getDeltaY() {
        return deltaY;
    }

    @Override
    public void update() {
        deltaX = 0;
        deltaY = 0;
        if (targetX != scrollX) {
            float diff = targetX - scrollX;
            float amount = scrollSpeed * GameView.frameTime;
            if (Math.abs(diff) < amount) amount = diff;
            else amount *= Math.signum(diff);

            scrollX += amount;
            deltaX = amount;
        }
        if (targetY != scrollY) {
            float diff = targetY - scrollY;
            float amount = scrollSpeed * GameView.frameTime;
            if (Math.abs(diff) < amount) amount = diff;
            else amount *= Math.signum(diff);

            scrollY += amount;
            deltaY = amount;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        //  원본 이미지에서 잘라낼 영역 설정
        srcRect.set((int)scrollX, (int)scrollY, (int)scrollX + (int)Metrics.width, (int)scrollY + (int)Metrics.height);

        //  화면 전체에 채우도록 설정 (900x1600 화면 전체)
        dstRect.set(0, 0, Metrics.width, Metrics.height);

        //  비율 그대로 유지하면서 srcRect의 내용을 dstRect로 매핑 (크기 조절 → 자르기만 함)
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
