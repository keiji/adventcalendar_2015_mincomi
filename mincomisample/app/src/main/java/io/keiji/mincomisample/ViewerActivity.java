package io.keiji.mincomisample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.keiji.mincomisample.view.ViewerView;

public class ViewerActivity extends AppCompatActivity {

    private ViewerView mViewerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewerView = new ViewerView(this);
        setContentView(mViewerView);
    }
}
