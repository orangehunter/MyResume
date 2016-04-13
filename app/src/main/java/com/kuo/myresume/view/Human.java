package com.kuo.myresume.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Kuo on 2016/4/13.
 */
public class Human {

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

    public Human(int countFrame, int frameWidth, Rect dstRect) {
        this.frameWidth = frameWidth;
        this.countFrame = countFrame;
        this.dstRect = dstRect;
    }

    public void drawHuman(Canvas canvas, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void update(int verticalCurFrame, int horizontalDirection) {
        long time  = System.currentTimeMillis();

        if ( time > lastFrameChangeTime + frameLengthInMilliseconds) {
            lastFrameChangeTime = time;
            currentFrame++;
            if (currentFrame >= countFrame) {
                currentFrame = 0;
            }

            int width = dstRect.width();
            dstRect.left += moveSpeed * horizontalDirection;
            dstRect.right = dstRect.left + width;
        }

        srcRect.left = currentFrame * frameWidth;
        srcRect.right = srcRect.left + frameWidth;
        srcRect.top = verticalCurFrame * frameWidth;
        srcRect.top = srcRect.top + frameWidth;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void updateIdle() {

    }
}
