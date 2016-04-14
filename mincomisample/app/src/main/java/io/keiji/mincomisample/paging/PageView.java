package io.keiji.mincomisample.paging;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import io.keiji.mincomisample.paging.entity.Align;
import io.keiji.mincomisample.paging.entity.Page;

public class PageView extends View {
    private static final String TAG = PageView.class.getSimpleName();

    private final Paint paint = new Paint();

    @Nullable
    private Page page;

    @Nullable
    private Align align;

    @NonNull
    private final Viewport viewport;

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        handler = new MyHandler(this);
        viewport = new Viewport();

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(60f);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewport.onSizeChanged(w, h, oldw, oldh);
    }

    public void setPage(@Nullable Page page, @NonNull Align align) {
        this.page = page;
        this.align = align;

        viewport.setAlign(align);

        if (page == null) {
            return;
        }

        if (bitmap == null) {
            new LoadThread(page.getFileName()).start();
        }
    }

    private Bitmap bitmap;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (page == null || bitmap == null) {
            return;
        }

        if (!viewport.isInitialized()) {
            return;
        }

        if (align == Align.Left) {
            canvas.drawColor(Color.DKGRAY);
        } else {
            canvas.drawColor(Color.LTGRAY);
        }

        int sc = canvas.save();
        canvas.translate(viewport.getMarginLeft(), viewport.getMarginTop());
        canvas.scale(viewport.getScale(), viewport.getScale());

        canvas.drawBitmap(bitmap, 0, 0, paint);

        canvas.restoreToCount(sc);

        canvas.drawText(align.name() + ": " + String.valueOf(page.getNumber()), 50, 150, paint);
    }

    public void destroy() {
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }

    private final Handler handler;

    private static class MyHandler extends Handler {
        private static final int HANDLE_REFRESH = 0x01;

        @NonNull
        private final WeakReference<PageView> pageView;

        private MyHandler(@NonNull PageView pageView) {
            this.pageView = new WeakReference<>(pageView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (pageView.get() == null) {
                return;
            }

            switch (msg.what) {
                case HANDLE_REFRESH:
                    pageView.get().invalidate();
            }
        }
    }

    /**
     * TODO: 非同期処理はもっとちゃんと実装する.
     */
    private class LoadThread extends Thread {
        private final String fileName;

        public LoadThread(@NonNull String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            InputStream is = null;
            try {
                is = getContext().getAssets().open(fileName);
                bitmap = BitmapFactory.decodeStream(is);
                viewport.setContentSize(bitmap.getWidth(), bitmap.getHeight());

                handler.sendEmptyMessage(MyHandler.HANDLE_REFRESH);
            } catch (IOException e) {
                Log.e(TAG, "IOException", e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

}
