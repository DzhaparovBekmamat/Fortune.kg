package com.example.fortunekg.wheel;

import com.example.fortunekg.R;

public class Wheel extends Thread {

    public interface WheelListener {
        void newImage(int img);
    }

    private static final int[] drawables = {R.drawable.kurut, R.drawable.kumys, R.drawable.samsa, R.drawable.manty, R.drawable.lagman, R.drawable.plov};
    public int currentIndex;
    private final WheelListener wheelListener;
    private final long frameDuration;
    private final long startIn;
    private boolean isStarted;

    public Wheel(WheelListener wheelListener, long frameDuration, long startIn) {
        this.wheelListener = wheelListener;
        this.frameDuration = frameDuration;
        this.startIn = startIn;
        currentIndex = 0;
        isStarted = true;
    }

    public void nextImg() {
        currentIndex++;

        if (currentIndex == drawables.length) {
            currentIndex = 0;
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(startIn);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (isStarted) {
            try {
                Thread.sleep(frameDuration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nextImg();

            if (wheelListener != null) {
                wheelListener.newImage(drawables[currentIndex]);
            }
        }
    }

    public void stopWheel() {
        isStarted = false;
    }
}