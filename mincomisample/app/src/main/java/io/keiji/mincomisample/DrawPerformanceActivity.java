package io.keiji.mincomisample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.keiji.mincomisample.view.BitmapView;
import io.keiji.mincomisample.view.SurfaceBitmapView;
import io.keiji.mincomisample.view.TextureBitmapView;

public class DrawPerformanceActivity extends AppCompatActivity {

    private TextureBitmapView mTextureBitmapView;
    private SurfaceBitmapView mSurfaceBitmapView;
    private BitmapView mBitmapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextureBitmapView = new TextureBitmapView(this);
        mSurfaceBitmapView = new SurfaceBitmapView(this);
        mBitmapView = new BitmapView(this);
        setContentView(mBitmapView);
    }
}
