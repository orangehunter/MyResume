package com.kuo.myresume.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.kuo.myresume.R;

/*
 * Created by Kuo on 2016/4/11.
 */
public class MyView extends GameManagerView {

    private Bitmap background, human, button;

    private GameButton rightButton, leftButton;

    private Human humanObj;

    private Paint bgPaint;

    public MyView(Context context) {
        this(context, null, 0);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        bgPaint = new Paint();
        bgPaint.setColor(Color.RED);
        bgPaint.setTextSize(150);

    }

    @Override
    public void onLoadData(int width, int height) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.map_1),
                width, height, true);


        human = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.org_a01, options);
        button = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.movetile01, options);

        humanObj = new Human(3, human.getWidth() / 3, new Rect(100, getHeight() - 500, 300, getHeight() - 300));
        rightButton = new GameButton(getContext(), new Rect(32, 64, 64, 96), new Rect(0, 64, 32, 96), new Rect(250, getHeight() - 200, 350, getHeight() - 100));
        leftButton = new GameButton(getContext(), new Rect(32, 32, 64, 64), new Rect(0, 32, 32, 64), new Rect(100, getHeight() - 200, 200, getHeight() - 100));
    }

    @Override
    public void drawBackground(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
    }

    @Override
    public void drawButtons(Canvas canvas) {
        rightButton.drawButton(canvas, button);
        leftButton.drawButton(canvas, button);
    }

    @Override
    public void drawChar(Canvas canvas) {
        humanObj.drawHuman(canvas, human);
    }

    @Override
    public void drawFps(Canvas canvas, long fps) {
        canvas.drawText(fps + "", 10, 100, bgPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        leftButton.touchButton(event);
        rightButton.touchButton(event);
        return true;
    }

    @Override
    public void update() {

        if(rightButton.isClick()) {
            humanObj.update(2, 1);
        } else if(leftButton.isClick()) {
            humanObj.update(1, -1);
        }
    }
}
