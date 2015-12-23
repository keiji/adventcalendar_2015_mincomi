package io.keiji.mincomisample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.keiji.mincomisample.R;

public class RankingFragment extends Fragment {

    private ViewPager mViewPager;

    public static RankingFragment newInstance() {
        RankingFragment fragment = new RankingFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    public RankingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        // XML側で指定
//        NestedScrollView nestedScrollView = (NestedScrollView) view.findViewById(R.id.scroll_view);
//        nestedScrollView.setFillViewport(true);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.ranking_tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.ranking_container);
        mViewPager.setAdapter(new Adapter(getChildFragmentManager()));

        tabLayout.setupWithViewPager(mViewPager);


        return view;
    }

    private static class Adapter extends FragmentStatePagerAdapter {

        private static final String[] sTitles = {
                "閲覧週間",
                "閲覧月間",
                "オススメ",
                "ピックアップ"};

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return sTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
            return DummyFragment.newInstance(sTitles[position]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sTitles[position];
        }
    }

    public static class DummyFragment extends Fragment {

        private static final String KEY_TITLE = "key_title";

        public static DummyFragment newInstance(String title) {
            DummyFragment fragment = new DummyFragment();

            Bundle args = new Bundle();
            args.putString(KEY_TITLE, title);
            fragment.setArguments(args);

            return fragment;
        }

        public DummyFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            String title = getArguments().getString(KEY_TITLE);

            TextView textView = new TextView(getContext());
            textView.setText(title);

            return textView;
        }
    }
}
