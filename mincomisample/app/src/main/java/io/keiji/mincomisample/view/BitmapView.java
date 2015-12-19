package io.keiji.mincomisample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import java.util.Locale;

import io.keiji.mincomisample.R;

public class BitmapView extends View {
    private static final String TAG = BitmapView.class.getSimpleName();

    private static final int COUNT = 1000;

    private static final Paint PAINT = new Paint();

    private final Bitmap mBitmap;

    private long elapsedTime;
    private int count;

    public BitmapView(Context context) {
        super(context);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmp500x600);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long start = Debug.threadCpuTimeNanos();

        canvas.drawBitmap(mBitmap, 0, 0, PAINT);

        long elapsed = Debug.threadCpuTimeNanos() - start;
        elapsedTime += elapsed;

        count++;

        if (count < COUNT) {
            invalidate();
        } else {
            Log.d(TAG, String.format(Locale.US, "elapsedTime:%d, avg:%.2f (ns)", elapsedTime, ((float) elapsedTime / count)));

        }
    }
}
