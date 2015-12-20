package io.keiji.mincomisample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import io.keiji.mincomisample.R;

public class ViewerView extends View {
    private static final String TAG = ViewerView.class.getSimpleName();

    private static final Paint PAINT = new Paint();

    private final OverScroller mScroller;
    private final GestureDetectorCompat mGestureDetector;
    private final ScaleGestureDetector mScaleGestureDetector;

    private float mLastScaleFactor;

    private final ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener
            = new ScaleGestureDetector.OnScaleGestureListener() {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = 1 + detector.getScaleFactor() - mLastScaleFactor;

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

    private final GestureDetector.OnGestureListener mOnGestureListener
            = new GestureDetector.OnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mDestRect.offset(-distanceX, -distanceY);
            invalidate();

            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            int overScroll = mBitmap.getWidth() / 2;

            mScroller.fling(Math.round(mDestRect.left), Math.round(mDestRect.top),
                    Math.round(velocityX), Math.round(velocityY),
                    0, Math.round(getWidth() - mDestRect.width()),
                    0, Math.round(getHeight() - mDestRect.height()),
                    overScroll, overScroll);

            ViewCompat.postInvalidateOnAnimation(ViewerView.this);

            return true;
        }
    };

    private final Bitmap mBitmap;

    private final Rect mSrcRect = new Rect();
    private final RectF mDestRect = new RectF();
    private float mFocusX;
    private float mFocusY;


    public ViewerView(Context context) {
        super(context);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        mSrcRect.set(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mDestRect.set(mSrcRect);

        mScroller = new OverScroller(context);
        mGestureDetector = new GestureDetectorCompat(context, mOnGestureListener);

        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);

        setFocusable(true);
    }


    private static final float CIRCLE_RADIUS = 10f;

    private static final Paint CIRCLE_PAINT = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, mSrcRect, mDestRect, PAINT);

        canvas.drawCircle(mFocusX, mFocusY, CIRCLE_RADIUS, CIRCLE_PAINT);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScroller.forceFinished(true);

        boolean handled = mScaleGestureDetector.onTouchEvent(event);

        handled = mGestureDetector.onTouchEvent(event);

        Log.d(TAG, "handled " + handled);

        return handled | super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()) {
            mDestRect.offsetTo(mScroller.getCurrX(), mScroller.getCurrY());

            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setScale(float scale, float focusX, float focusY) {
        mFocusX = focusX;
        mFocusY = focusY;

        float newWidth = mDestRect.width() * scale;
        float newHeight = mDestRect.height() * scale;

        if (newWidth > getWidth()) {
            return;
        }

        if (newHeight > getHeight()) {
            return;
        }

        float scrollX = mDestRect.width() - newWidth;
        float scrollY = mDestRect.height() - newHeight;

        mDestRect.right = mDestRect.left + newWidth;
        mDestRect.bottom = mDestRect.top + newHeight;

        scrollX *= (focusX / getWidth());
        scrollY *= (focusY / getHeight());

        mDestRect.offset(scrollX, scrollY);

        invalidate();
    }

}
