package com.kuo.myresume.script;

import android.graphics.Bitmap;
import android.graphics.RectF;

/**
 * Created by Kuo on 2016/4/11.
 */
public class ImageSprite {

    private RectF dstRect, sourceRect;

    private int size;

    private int count = 0;

    private long updateTime;

    public ImageSprite(Bitmap bitmap, RectF dstRect, long updateTime) {

        this.dstRect = dstRect;
        this.updateTime = updateTime;

        sourceRect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        size = (int) (sourceRect.width() / dstRect.width());

    }

    public RectF getRstRect(long time) {

        float left = 0, right = 0, top = 0, bottom = 0;

        if(time > updateTime) {
            left = dstRect.left * count;
            right = dstRect.right * count;
            top = dstRect.top * count;
            bottom = dstRect.bottom * count;
        }

        count++;

        return new RectF(left, top, right, bottom);
    }

}
