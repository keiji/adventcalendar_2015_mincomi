package io.keiji.mincomisample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import io.keiji.mincomisample.R;

public class ZoomView extends View {
    private static final String TAG = ZoomView.class.getSimpleName();

    private static final float CIRCLE_RADIUS = 10f;

    private static final Paint PAINT = new Paint();
    private static final Paint CIRCLE_PAINT = new Paint();

    static {
        PAINT.setAntiAlias(true);

        CIRCLE_PAINT.setColor(Color.RED);
    }

    private final Rect mSrcRect;
    private final Bitmap mBitmap;

    private final ScaleGestureDetector mScaleGestureDetector;

    public ZoomView(Context context) {
        super(context);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        mSrcRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());

        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);

        setFocusable(true);
    }

    private float mLastScaleFactor;

    private final ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener
            = new ScaleGestureDetector.OnScaleGestureListener() {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = 1 + detector.getScaleFactor() - mLastScaleFactor;

            Log.d(TAG, "scale = " + scale);
            setScale(scale, detector.getFocusX(), detector.getFocusY());

            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            mLastScaleFactor = detector.getScaleFactor();
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
        }
    };

    private float mFocusX;
    private float mFocusY;

    public void setScale(float scale, float focusX, float focusY) {
        mFocusX = focusX;
        mFocusY = focusY;

        float newWidth = mRect.width() * scale;
        float newHeight = mRect.height() * scale;

        float scrollX = mRect.width() - newWidth;
        float scrollY = mRect.height() - newHeight;

        mRect.right = mRect.left + newWidth;
        mRect.bottom = mRect.top + newHeight;

        scrollX *= (focusX / getWidth());
        scrollY *= (focusY / getHeight());

        mRect.offset(scrollX, scrollY);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean handled = mScaleGestureDetector.onTouchEvent(event);
        if (handled) {
            return true;
        }

        return super.onTouchEvent(event);
    }

    private RectF mRect;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mRect == null) {
            mRect = calculateRectFromBitmap(mBitmap, canvas.getWidth(), canvas.getHeight());
        }

        canvas.drawBitmap(mBitmap, mSrcRect, mRect, PAINT);

        canvas.drawCircle(mFocusX, mFocusY, CIRCLE_RADIUS, CIRCLE_PAINT);

    }

    private static RectF calculateRectFromBitmap(Bitmap bitmap, int viewWidth, int viewHeight) {
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();

        float ratio = Math.min(viewWidth / width, viewHeight / height);
        width *= ratio;
        height *= ratio;

        return new RectF(0, 0, width, height);
    }
}
