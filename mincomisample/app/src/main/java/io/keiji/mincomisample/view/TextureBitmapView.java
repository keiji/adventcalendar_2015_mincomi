package io.keiji.mincomisample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.os.Debug;
import android.util.Log;
import android.view.TextureView;

import java.util.Locale;

import io.keiji.mincomisample.R;

public class TextureBitmapView extends TextureView implements TextureView.SurfaceTextureListener {
    private static final String TAG = TextureBitmapView.class.getSimpleName();

    private static final Paint PAINT = new Paint();

    private static final int COUNT = 1000;

    private final Bitmap mBitmap;
    private DrawThread mDrawThread;

    public TextureBitmapView(Context context) {
        super(context);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmp500x600);

        setSurfaceTextureListener(this);

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (mDrawThread != null) {
            mDrawThread.mIsFinished = true;
            mDrawThread = null;
        }
        mDrawThread = new DrawThread();
        mDrawThread.start();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mDrawThread != null) {
            mDrawThread.mIsFinished = true;
            mDrawThread = null;
        }

        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    private class DrawThread extends Thread {
        volatile boolean mIsFinished;
        private int count = 0;
        private long elapsedTime;

        @Override
        public void run() {
            while (!mIsFinished && count < COUNT) {

                Canvas canvas = lockCanvas();
                if (canvas == null) {
                    return;
                }

                long start = Debug.threadCpuTimeNanos();

                canvas.drawBitmap(mBitmap, 0, 0, PAINT);

                long elapsed = Debug.threadCpuTimeNanos() - start;
                elapsedTime += elapsed;

                unlockCanvasAndPost(canvas);

                count++;
            }

            Log.d(TAG, String.format(Locale.US, "elapsedTime:%d, avg:%.2f (ns)", elapsedTime, ((float) elapsedTime / count)));

        }
    }
}
