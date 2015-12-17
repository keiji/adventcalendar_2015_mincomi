package io.keiji.mincomisample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

public class ScrollerView extends View {
    private static final String TAG = ScrollerView.class.getSimpleName();

    private static final int SIZE = 100;
    private static final Paint PAINT = new Paint();

    static {
        PAINT.setColor(Color.RED);
    }

    private Rect mRect;

    private final OverScroller mScroller;
    private final GestureDetectorCompat mGestureDetector;

    public ScrollerView(Context context) {
        super(context);

        mScroller = new OverScroller(context);
        mGestureDetector = new GestureDetectorCompat(context, mOnGestureListener);

        setFocusable(true);
    }

    GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener() {
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
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            mScroller.fling(mRect.centerX(), mRect.centerY(),
                    Math.round(velocityX), Math.round(velocityY),
                    0, getWidth() - SIZE, 0, getHeight() - SIZE,
                    SIZE / 2, SIZE / 2);

            ViewCompat.postInvalidateOnAnimation(ScrollerView.this);

            return true;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent " + event.getAction());

        boolean handled = mGestureDetector.onTouchEvent(event);
        if (handled) {
            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mRect == null) {
            mRect = new Rect(0, 0, SIZE, SIZE);
            int left = (canvas.getWidth() - SIZE) / 2;
            int top = (canvas.getHeight() - SIZE) / 2;
            mRect.offset(left, top);
        }

        canvas.drawRect(mRect, PAINT);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()) {
            mRect.offsetTo(mScroller.getCurrX(), mScroller.getCurrY());

            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
