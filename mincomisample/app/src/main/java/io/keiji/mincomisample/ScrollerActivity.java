package io.keiji.mincomisample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.keiji.mincomisample.view.ScrollerView;

public class ScrollerActivity extends AppCompatActivity {

    private ScrollerView mScrollerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScrollerView = new ScrollerView(this);
        setContentView(mScrollerView);
    }
}
