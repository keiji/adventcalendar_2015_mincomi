package io.keiji.mincomisample.paging.adapter;

import android.support.annotation.NonNull;

import io.keiji.mincomisample.paging.entity.Book;
import io.keiji.mincomisample.paging.entity.PageContainer;

/**
 * Created by keiji_ariyama on 4/14/16.
 */
public abstract class AbsPageAdapter {

    final Book book;

    protected AbsPageAdapter(@NonNull Book book) {
        this.book = book;
    }

    public abstract int getCount();

    public final PageContainer getPage(int position) {
        // ページの並びを反転
        return getPageInternal(getCount() - position - 1);
    }

    public abstract PageContainer getPageInternal(int position);

}
