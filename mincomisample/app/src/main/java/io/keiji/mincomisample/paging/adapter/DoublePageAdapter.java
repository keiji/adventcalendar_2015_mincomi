package io.keiji.mincomisample.paging.adapter;

import io.keiji.mincomisample.paging.entity.Align;
import io.keiji.mincomisample.paging.entity.Book;
import io.keiji.mincomisample.paging.entity.Page;
import io.keiji.mincomisample.paging.entity.PageContainer;

/**
 * Created by keiji_ariyama on 4/14/16.
 */
public class DoublePageAdapter extends AbsPageAdapter {

    public DoublePageAdapter(Book book) {
        super(book);
    }

    @Override
    public int getCount() {
        int basePageCount = book.getPageList().size();
        if (book.getStartPageAlign() == Align.Left) {
            basePageCount++;
        }
        return (basePageCount / 2) + (basePageCount % 2);
    }

    @Override
    public PageContainer getPageInternal(int position) {

        Page rightPage = null;
        Page leftPage = null;

        if (position == 0) {
            int pageIndex = 0;
            if (book.getStartPageAlign() == Align.Right) {
                rightPage = getPageByIndex(pageIndex);
                leftPage = getPageByIndex(pageIndex + 1);
            } else {
                leftPage = getPageByIndex(pageIndex);
            }
        } else {
            int pageIndex = position * 2 - 1;
            if (book.getStartPageAlign() == Align.Right) {
                pageIndex++;
            }
            rightPage = getPageByIndex(pageIndex);
            leftPage = getPageByIndex(pageIndex + 1);
        }

        return new PageContainer(leftPage, rightPage);
    }

    private Page getPageByIndex(int index) {
        if (index < 0) {
            return null;
        } else if (index > book.getPageList().size() - 1) {
            return null;
        }
        return book.getPageList().get(index);
    }
}
