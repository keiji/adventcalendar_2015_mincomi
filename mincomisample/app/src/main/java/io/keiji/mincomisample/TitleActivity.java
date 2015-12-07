package io.keiji.mincomisample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TitleActivity extends AppCompatActivity {

    private TextView mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_title_author);
        mStatus = (TextView) findViewById(R.id.status);

        processIntent(getIntent(), mStatus);
    }

    private void processIntent(Intent intent, TextView textView) {

        Uri uri = intent.getData();
        String titleId = uri.getQueryParameter("title");

        textView.setText("titleId = " + titleId);
    }

}
