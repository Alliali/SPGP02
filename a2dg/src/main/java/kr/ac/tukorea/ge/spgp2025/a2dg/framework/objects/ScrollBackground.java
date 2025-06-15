package kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class ScrollBackground extends Sprite {
    private final Rect srcRect = new Rect();    // 원본 이미지에서 자를 부분
    private final RectF dstRect = new RectF();  // 화면에 그릴 위치
    private float scrollX = 410, scrollY = 170;
    private float targetX = 410, targetY = 170;
    private final float scrollSpeed = 300.0f;
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean moveCheck = false;
    private boolean attackCheck = false;

    public ScrollBackground(int bitmapResId) {
        super(bitmapResId);
        setPosition(bitmap.getWidth() / 2f, bitmap.getHeight() / 2f, bitmap.getWidth(), bitmap.getHeight());
    }

    public void checkMove(boolean check) {
        moveCheck = check;
        if (leftDirection) move(-64, 0);
        if (rightDirection) move(64, 0);
        if (upDirection) move(0, -64);
        if (downDirection) move(0, 64);
        moveCheck = false;
    }

    public void checkAttack(boolean check) {
        attackCheck = check;
        attackCheck = false;
    }

    public void direction(float angle, boolean dir) {
        if (angle == 180.0f) {
            leftDirection = dir;
            rightDirection = upDirection = downDirection = false;
        } else if (angle == 0.0f) {
            rightDirection = dir;
            leftDirection = upDirection = downDirection = false;
        } else if (angle == 90.0f) {
            upDirection = dir;
            leftDirection = rightDirection = downDirection = false;
        } else if (angle == 270.0f) {
            downDirection = dir;
            leftDirection = rightDirection = upDirection = false;
        }
    }
    public void move(float dx, float dy) {
        targetX += dx;
        targetY += dy;

        // 스크롤 범위 제한 (선택사항)
//        if (scrollX < 0) scrollX = 0;
//        if (scrollY < 0) scrollY = 0;
//        if (scrollX > bitmap.getWidth() - Metrics.width) scrollX = bitmap.getWidth() - Metrics.width;
//        if (scrollY > bitmap.getHeight() - Metrics.height) scrollY = bitmap.getHeight() - Metrics.height;
    }

    @Override
    public void update() {
        float dx = targetX - scrollX;
        float dy = targetY - scrollY;

        float dist = (float)Math.sqrt(dx * dx + dy * dy);
        if (dist < 1.0f) return;

        float moveDist = scrollSpeed * GameView.frameTime;
        if (dist < moveDist) moveDist = dist;

        scrollX += dx / dist * moveDist;
        scrollY += dy / dist * moveDist;
    }

    @Override
    public void draw(Canvas canvas) {
        // 1️⃣ 원본 이미지에서 잘라낼 영역 설정
        srcRect.set((int)scrollX, (int)scrollY, (int)scrollX + (int)Metrics.width, (int)scrollY + (int)Metrics.height);

        // 2️⃣ 화면 전체에 채우도록 설정 (900x1600 화면 전체)
        dstRect.set(0, 0, Metrics.width, Metrics.height);

        // 3️⃣ 비율 그대로 유지하면서 srcRect의 내용을 dstRect로 매핑 (크기 조절 → 자르기만 함)
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
