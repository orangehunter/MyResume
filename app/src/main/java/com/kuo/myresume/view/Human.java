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

    Rect dstRect, srcRect;

    public static final int HUMAN_IDLE = 0;
    public static final int HUMAN_RUN = 1;

    int state = 0;

    int frameWidth = 0;

    int currentFrame = 0;

    int countFrame = 0;

    int moveSpeed = 15;

    Bitmap idleBitmap, runBitmap;

    SpriteAnimation spriteAnimation;

    public Human(int countFrame, int frameWidth, Rect dstRect, Bitmap runBitmap) {
        this.frameWidth = frameWidth;
        this.countFrame = countFrame;
        this.dstRect = dstRect;
        this.runBitmap = runBitmap;

        srcRect = new Rect();

        spriteAnimation = new SpriteAnimation(100, new Rect(0, 0, runBitmap.getWidth(), runBitmap.getHeight()), 319, 486, 10);
        spriteAnimation.setOnUpdateListener(this);
    }

    public void drawHuman(Canvas canvas, Bitmap bitmap) {
        canvas.drawBitmap(runBitmap, srcRect, dstRect, null);
    }

    public void update(int verticalCurFrame, int horizontalDirection) {
        spriteAnimation.start();
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void onUpdate(int currentHorizontalFrame, int currentVerticalFrame) {

        //int width = dstRect.width();
        //dstRect.left += moveSpeed * horizontalDirection;
        //dstRect.right = dstRect.left + width;

        srcRect.left = currentHorizontalFrame * spriteAnimation.getFrameWidth();
        srcRect.right = srcRect.left + spriteAnimation.getFrameWidth();

        srcRect.top = currentVerticalFrame * spriteAnimation.getFrameHeight();
        srcRect.bottom = srcRect.top + spriteAnimation.getFrameHeight();
    }
}
