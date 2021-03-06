package com.kuo.myresume.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.kuo.myresume.animation.SpriteAnimation;

/**
 * Created by Kuo on 2016/4/13.
 */
public class Human implements SpriteAnimation.OnUpdateListener {

    long lastFrameChangeTime;
    long frameLengthInMilliseconds = 100;

    Rect dstRect, srcRect = new Rect();

    public static final int HUMAN_IDLE = 0;
    public static final int HUMAN_RUN = 1;
    public static final int HUMAN_JUMP = 2;

    int state = 0;

    int moveSpeed = 8;

    Bitmap idleBitmap, runBitmap;

    SpriteAnimation idleAnimation, runAnimation;

    public Human(final Rect dstRect, Bitmap idleBitmap, Bitmap runBitmap) {

        this.dstRect = dstRect;

        this.idleBitmap = idleBitmap;

        this.runBitmap = runBitmap;

        idleAnimation = new SpriteAnimation(50, new Rect(0, 0, idleBitmap.getWidth(), idleBitmap.getHeight()), 319, 486, 10);
        idleAnimation.setOnUpdateListener(this);

        runAnimation = new SpriteAnimation(50, new Rect(0, 0, runBitmap.getWidth(), runBitmap.getHeight()), 415, 507, 10);
        runAnimation.setOnUpdateListener(new SpriteAnimation.OnUpdateListener() {
            @Override
            public void onUpdate(int currentHorizontalFrame, int currentVerticalFrame) {

                int width = dstRect.width();
                dstRect.left += moveSpeed * direction;
                dstRect.right = dstRect.left + width;

                int left = currentHorizontalFrame * runAnimation.getFrameWidth();
                int right = left + runAnimation.getFrameWidth();
                int top = currentVerticalFrame * runAnimation.getFrameHeight();
                int bottom = top + runAnimation.getFrameHeight();

                srcRect.set(left, top, right, bottom);
            }
        });
    }

    public void drawHuman(Canvas canvas) {
        switch (state) {
            case HUMAN_IDLE:
                canvas.drawBitmap(idleBitmap, srcRect, dstRect, null);
                break;
            case HUMAN_RUN:
                canvas.drawBitmap(runBitmap, srcRect, dstRect, null);
                break;
        }
    }

    public void update(int state) {

        switch (state) {
            case HUMAN_IDLE:
                idleAnimation.start();
                break;
            case HUMAN_RUN:
                runAnimation.start();
                break;
        }

        this.state = state;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    private int direction = 1;

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void onUpdate(int currentHorizontalFrame, int currentVerticalFrame) {


        int left = currentHorizontalFrame * idleAnimation.getFrameWidth();
        int right = left + idleAnimation.getFrameWidth();
        int top = currentVerticalFrame * idleAnimation.getFrameHeight();
        int bottom = top + idleAnimation.getFrameHeight();

        srcRect.set(left, top, right, bottom);
    }
}
