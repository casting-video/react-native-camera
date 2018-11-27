package com.google.android.cameraview;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

public final class CameraBackgroundHandler {
    private static Handler bgHandler = null;
    private static final String TAG = "CameraBackgroundHandler";

    public static void post(@NonNull String label, @NonNull Runnable block) {
        if (bgHandler == null) {
            HandlerThread handlerThread = new HandlerThread(TAG);
            handlerThread.start();
            Looper looper = handlerThread.getLooper();
            bgHandler = new Handler(looper);
        }
        bgHandler.post(() -> {
            Log.d(TAG, "<<<< " + label);
            long startTime = System.currentTimeMillis();
            block.run();
            Log.d(TAG, String.format(">>>> %s took %.3f sec", label, (double)(System.currentTimeMillis() - startTime) / 1000));
        });
    }
}
