package com.kuo.myresume.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by Kuo on 2016/4/12.
 */
public class GameButton {

    private Context context;

    private Rect dstRect, touchRect, defaultRect;

    private boolean isClick = false;

    public GameButton(Context context, Rect defaultRect, Rect touchRect, Rect dstRect) {

        this.context = context;
        this.defaultRect = defaultRect;
        this.touchRect = touchRect;
        this.dstRect = dstRect;

    }

    public void drawButton(Canvas canvas, Bitmap bitmap) {

        if(!isClick)
            canvas.drawBitmap(bitmap, defaultRect, dstRect, null);
        else
            canvas.drawBitmap(bitmap, touchRect, dstRect, null);

    }

    public boolean isClick() {
        return isClick;
    }

    public boolean touchButton(MotionEvent e) {

        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            isClick = dstRect.contains((int) e.getX(), (int) e.getY());
        } else if(e.getAction() == MotionEvent.ACTION_UP){
            isClick = false;
        }

        return isClick;
    }


}
