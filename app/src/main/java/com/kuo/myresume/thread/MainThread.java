package com.kuo.myresume.thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.kuo.myresume.view.GameManagerView;

/*
 * Created by Kuo on 2016/4/12.
 */
public class MainThread extends Thread{

    private GameManagerView gameManagerView;

    private boolean loop = true;

    private long timeThisFrame;

    private long fps;

    private final SurfaceHolder surfaceHolder;

    public MainThread(GameManagerView gameManagerView, SurfaceHolder surfaceHolder) {
        this.gameManagerView =  gameManagerView;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        while(loop) {

            long startFrameTime = System.currentTimeMillis();

            synchronized(surfaceHolder) {
                Canvas canvas = surfaceHolder.lockCanvas();

                gameManagerView.update();
                gameManagerView.drawBackground(canvas);
                gameManagerView.drawChar(canvas);
                gameManagerView.drawButtons(canvas);
                gameManagerView.drawFps(canvas, fps);

                surfaceHolder.unlockCanvasAndPost(canvas);

                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }
            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
