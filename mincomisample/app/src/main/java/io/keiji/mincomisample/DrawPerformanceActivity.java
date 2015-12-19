package io.keiji.mincomisample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.keiji.mincomisample.view.BitmapView;
import io.keiji.mincomisample.view.SurfaceBitmapView;

public class DrawPerformanceActivity extends AppCompatActivity {

    private SurfaceBitmapView mSurfaceBitmapView;
    private BitmapView mBitmapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSurfaceBitmapView = new SurfaceBitmapView(this);
        mBitmapView = new BitmapView(this);
        setContentView(mSurfaceBitmapView);
    }
}
