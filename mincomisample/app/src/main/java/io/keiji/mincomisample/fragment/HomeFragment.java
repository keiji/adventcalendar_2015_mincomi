package io.keiji.mincomisample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import io.keiji.mincomisample.R;

public class HomeFragment extends Fragment {

    private static final String KEY_DEFAULT_PROGRESS = "key_default_progress";

    public static HomeFragment newInstance(int progress) {
        HomeFragment fragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_DEFAULT_PROGRESS, progress);
        fragment.setArguments(args);

        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof SeekBar.OnSeekBarChangeListener) {
            mOnSeekBarChangeListener = (SeekBar.OnSeekBarChangeListener) context;
        }
    }

    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;

    private SeekBar mSeekBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        int defaultProgress = getArguments().getInt(KEY_DEFAULT_PROGRESS);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        mSeekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
        mSeekBar.setProgress(defaultProgress);

        return view;
    }
}
