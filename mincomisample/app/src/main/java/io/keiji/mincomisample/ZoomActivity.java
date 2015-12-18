package io.keiji.mincomisample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.keiji.mincomisample.view.ZoomView;

public class ZoomActivity extends AppCompatActivity {

    private ZoomView mZoomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mZoomView = new ZoomView(this);
        setContentView(mZoomView);
    }
}
