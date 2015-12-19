package io.keiji.mincomisample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Debug;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Locale;

import io.keiji.mincomisample.R;

public class SurfaceBitmapView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = SurfaceBitmapView.class.getSimpleName();

    private static final Paint PAINT = new Paint();

    private static final int COUNT = 1000;

    private final Bitmap mBitmap;
    private DrawThread mDrawThread;

    public SurfaceBitmapView(Context context) {
        super(context);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmp500x600);

        getHolder().addCallback(this);
    }

    private class DrawThread extends Thread {
        volatile boolean mIsFinished;
        private int count = 0;
        private long elapsedTime;

        @Override
        public void run() {
            while (!mIsFinished && count < COUNT) {
                Canvas canvas = getHolder().lockCanvas();
                if (canvas == null) {
                    return;
                }

                long start = Debug.threadCpuTimeNanos();

                canvas.drawBitmap(mBitmap, 0, 0, PAINT);

                long elapsed = Debug.threadCpuTimeNanos() - start;
                elapsedTime += elapsed;

                getHolder().unlockCanvasAndPost(canvas);

                count++;
            }

            Log.d(TAG, String.format(Locale.US, "elapsedTime:%d, avg:%.2f (ns)", elapsedTime, ((float) elapsedTime / count)));

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        if (mDrawThread != null) {
            mDrawThread.mIsFinished = true;
            mDrawThread = null;
        }
        mDrawThread = new DrawThread();
        mDrawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mDrawThread != null) {
            mDrawThread.mIsFinished = true;
            mDrawThread = null;
        }
    }
}
