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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int[] DRAWABLE_ARRAY = new int[]{
            R.drawable.ic_archive_black_48dp,
            R.drawable.ic_enhanced_encryption_black_48dp,
            R.drawable.ic_no_encryption_black_48dp,
            R.drawable.ic_vpn_key_black_48dp,
            R.drawable.ic_person_black_48dp,
    };
    private TabLayout mTabLayout;

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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mPageControl = (LinearLayout) findViewById(R.id.pagecontrol);
        for (int foo : DRAWABLE_ARRAY) {
            ImageView indicator = generateIndicator();
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
        mTabLayout.addTab(mTabLayout.newTab().setText("ホーム"));
        mTabLayout.addTab(mTabLayout.newTab().setText("ランキング"));
        mTabLayout.addTab(mTabLayout.newTab().setText("お気に入り"));
        mTabLayout.addTab(mTabLayout.newTab().setText("その他"));
    }

    private void selectIndicator(ImageView indicator) {
        if (mNowSelectedIndicator != null) {
            mNowSelectedIndicator.setSelected(false);
        }

        mNowSelectedIndicator = indicator;
        mNowSelectedIndicator.setSelected(true);
    }

    private ImageView generateIndicator() {
        ImageView indicator = new ImageView(this);
        indicator.setImageResource(R.drawable.page_indicator);
        indicator.setSelected(false);

        return indicator;
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
