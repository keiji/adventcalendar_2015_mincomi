package io.keiji.mincomisample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.keiji.mincomisample.fragment.FavoritesFragment;
import io.keiji.mincomisample.fragment.HomeFragment;
import io.keiji.mincomisample.fragment.RankingFragment;


public class MainActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener, TabLayout.OnTabSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int[] DRAWABLE_ARRAY = new int[]{
            R.drawable.ic_archive_black_48dp,
            R.drawable.ic_enhanced_encryption_black_48dp,
            R.drawable.ic_no_encryption_black_48dp,
            R.drawable.ic_vpn_key_black_48dp,
            R.drawable.ic_person_black_48dp,
    };

    private TabLayout mTabLayout;
    private Toolbar mToolbar;

    private TabLayout.Tab mHomeTab;
    private TabLayout.Tab mRankingTab;
    private TabLayout.Tab mFavoritesTab;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private final List<ImageView> mIndicatorList = new ArrayList<>();

    private ViewPager mViewPager;
    private LinearLayout mPageControl;

    private ImageView mNowSelectedIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabs);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        mPageControl = (LinearLayout) findViewById(R.id.pagecontrol);
        for (int foo : DRAWABLE_ARRAY) {
            ImageView indicator = (ImageView) View.inflate(this, R.layout.indicator, null);
            indicator.setImageResource(R.drawable.page_indicator);
            indicator.setSelected(false);

            mIndicatorList.add(indicator);

            mPageControl.addView(indicator);
        }

        selectIndicator(mIndicatorList.get(0));

        Adapter adapter = new Adapter(this, DRAWABLE_ARRAY);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                selectIndicator(mIndicatorList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setOnTabSelectedListener(this);

        mHomeTab = mTabLayout.newTab().setText("ホーム");
        mRankingTab = mTabLayout.newTab().setText("ランキング");
        mFavoritesTab = mTabLayout.newTab().setText("お気に入り");

        mTabLayout.addTab(mHomeTab);
        mTabLayout.addTab(mRankingTab);
        mTabLayout.addTab(mFavoritesTab);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance(50), HomeFragment.class.getSimpleName())
                    .commit();
        }
    }

    private void selectIndicator(ImageView indicator) {
        if (mNowSelectedIndicator != null) {
            mNowSelectedIndicator.setSelected(false);
        }

        mNowSelectedIndicator = indicator;
        mNowSelectedIndicator.setSelected(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_option:
                Intent intent = SettingActivity.newIntent(this);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onProgressChanged" + progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // do nothing
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab == mHomeTab) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance(50), HomeFragment.class.getSimpleName())
                    .commit();
        } else if (tab == mRankingTab) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, RankingFragment.newInstance(), RankingFragment.class.getSimpleName())
                    .commit();
        } else if (tab == mFavoritesTab) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, FavoritesFragment.newInstance(), FavoritesFragment.class.getSimpleName())
                    .commit();
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        // do nothing
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        // do nothing
    }

    private static class Adapter extends PagerAdapter {

        private WeakReference<Context> context;
        private final int[] drawables;

        Adapter(@NonNull Context context, @NonNull int[] drawables) {
            this.context = new WeakReference<>(context);
            this.drawables = drawables;
        }

        @Override
        public int getCount() {
            return drawables.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int drawable = drawables[position];

            // View を生成
            ImageView imageView = new ImageView(context.get());
            imageView.setImageResource(drawable);

            // コンテナに追加
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            if (!(object instanceof ImageView)) {
                return false;
            }
            return view == object;
        }
    }
}
