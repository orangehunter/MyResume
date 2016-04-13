package com.kuo.myresume.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kuo.myresume.thread.MainThread;

/**
 * Created by Kuo on 2016/4/11.
 */
public abstract class GameManagerView extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread mainThread;

    private SurfaceHolder mSurfaceHolder;

    public GameManagerView(Context context) {
        this(context, null, 0);
    }

    public GameManagerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameManagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mainThread = new MainThread(GameManagerView.this, mSurfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        onLoadData(width, height);
        mainThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public abstract void onLoadData(int width, int height);

    public abstract void update();

    public abstract void drawBackground(Canvas canvas);

    public abstract void drawButtons(Canvas canvas);

    public abstract void drawChar(Canvas canvas);

    public abstract void drawFps(Canvas canvas, long fps);
}
