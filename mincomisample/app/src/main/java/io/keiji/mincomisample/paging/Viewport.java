package io.keiji.mincomisample.paging;

import android.support.annotation.NonNull;

import io.keiji.mincomisample.paging.entity.Align;

/**
 * Created by keiji_ariyama on 4/14/16.
 */
public class Viewport {

    private Align align = Align.Center;

    private float scrollX;
    private float scrollY;

    private float maxScrollX;
    private float maxScrollY;

    private float viewWidth;
    private float viewHeight;

    private float contentWidth;
    private float contentHeight;

    private float scale;

    private float minScale;
    private float maxScale;

    private float marginTop;
    private float marginLeft;

    private boolean isInitialized;

    public float getScrollX() {
        return scrollX;
    }

    public float getScrollY() {
        return scrollY;
    }

    public float getScale() {
        return scale;
    }

    public float getMarginTop() {
        return marginTop;
    }

    public float getMarginLeft() {
        return marginLeft;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        if (viewWidth == width && viewHeight == height) {
            return;
        }

        viewWidth = width;
        viewHeight = height;

        calculateScale();
    }

    public void setAlign(@NonNull Align align) {
        this.align = align;

        calculateScale();
    }

    public void setContentSize(int width, int height) {
        contentWidth = width;
        contentHeight = height;

        calculateScale();
    }

    private void calculateScale() {
        scale = minScale = Math.min(viewWidth / contentWidth, viewHeight / contentHeight);
        maxScale = minScale * 5.0f;

        marginTop = (viewHeight - contentHeight * scale) / 2;
        float left = viewWidth - contentWidth * scale;

        switch (align) {
            case Left:
                marginLeft = left;
                break;
            case Right:
                marginLeft = 0;
                break;
            case Center:
                marginLeft = left / 2;
                break;
        }

        isInitialized = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("align = ").append(align.name())
                .append(" ")
                .append("scale = ").append(scale)
                .append(" ")
                .append("marginLeft = ").append(marginLeft)
                .append(" ")
                .append("marginTop = ").append(marginTop);

        return sb.toString();
    }


}
