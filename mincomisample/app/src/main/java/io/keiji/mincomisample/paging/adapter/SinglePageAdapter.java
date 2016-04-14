package io.keiji.mincomisample.paging.adapter;

import java.util.List;

import io.keiji.mincomisample.paging.entity.Align;
import io.keiji.mincomisample.paging.entity.Book;
import io.keiji.mincomisample.paging.entity.Page;
import io.keiji.mincomisample.paging.entity.PageContainer;

/**
 * Created by keiji_ariyama on 4/14/16.
 */
public class SinglePageAdapter extends AbsPageAdapter {

    public SinglePageAdapter(Book book) {
        super(book);
    }

    @Override
    public int getCount() {
        return book.getPageList().size();
    }

    @Override
    public PageContainer getPageInternal(int position) {

        List<Page> pageList = book.getPageList();

        Page basePage = pageList.get(position);

        // 見開きでなければ中央配置
        if (!basePage.isSpread()) {
            return new PageContainer(basePage);
        }

        Align basePageAlign = book.getStartPageAlign();
        if (position % 2 == 1) {
            basePageAlign = Align.flip(basePageAlign);
        }

        Page leftPage = null;
        Page rightPage = null;

        if (basePageAlign == Align.Left) {
            leftPage = basePage;
        } else {
            rightPage = basePage;
        }

        return new PageContainer(leftPage, rightPage);
    }
}
