package io.keiji.mincomisample.paging;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import io.keiji.mincomisample.R;
import io.keiji.mincomisample.paging.adapter.AbsPageAdapter;
import io.keiji.mincomisample.paging.adapter.DoublePageAdapter;
import io.keiji.mincomisample.paging.adapter.SinglePageAdapter;
import io.keiji.mincomisample.paging.entity.Align;
import io.keiji.mincomisample.paging.entity.Book;
import io.keiji.mincomisample.paging.entity.Page;
import io.keiji.mincomisample.paging.entity.PageContainer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @NonNull
    private AbsPageAdapter getPageAdapter() {
        List<Page> pageList = new ArrayList<>();
        pageList.add(new Page(1, false, "page1.png"));
        pageList.add(new Page(2, false, "page2.png"));
        pageList.add(new Page(3, false, "page3.png"));
        pageList.add(new Page(4, false, "page4.png"));
        pageList.add(new Page(5, false, "page5.png"));
        pageList.add(new Page(6, false, "page6.png"));
        pageList.add(new Page(7, false, "page7.png"));
        pageList.add(new Page(8, true, "page8.png"));
        pageList.add(new Page(9, true, "page9.png"));
        pageList.add(new Page(10, false, "page10.png"));
        pageList.add(new Page(11, false, "page11.png"));
        pageList.add(new Page(12, false, "page12.png"));
        pageList.add(new Page(13, false, "page13.png"));
        pageList.add(new Page(14, false, "page14.png"));
        pageList.add(new Page(15, false, "page15.png"));
        pageList.add(new Page(16, false, "page16.png"));

        Book book = new Book(Align.Left, pageList);

        AbsPageAdapter pageAdapter;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            pageAdapter = new DoublePageAdapter(book);
        } else {
            pageAdapter = new SinglePageAdapter(book);
        }

        return pageAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_main);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.viewpagerContainer);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setId(getViewPagerId());
        frameLayout.addView(viewPager);

        AbsPageAdapter pageAdapter = getPageAdapter();
        viewPager.setAdapter(new Adapter(getSupportFragmentManager(), pageAdapter));
        viewPager.setCurrentItem(pageAdapter.getCount() - 1);
    }

    /**
     * 画面の縦横切り替え時にFragmentにゴミが残る問題を解決するため、ViewPagerに別のIDを設定する.
     */
    public int getViewPagerId() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return R.id.viewPagerIdDouble;
        } else {
            return R.id.viewPagerIdSingle;
        }
    }

    private static class Adapter extends FragmentPagerAdapter {

        private final AbsPageAdapter adapter;

        public Adapter(@NonNull FragmentManager fm, AbsPageAdapter adapter) {
            super(fm);
            this.adapter = adapter;
        }

        @Override
        public Fragment getItem(int position) {
            PageContainer container = adapter.getPage(position);
            return PageFragment.newInstance(container);
        }

        @Override
        public int getCount() {
            return adapter.getCount();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
