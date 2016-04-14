package io.keiji.mincomisample.paging.entity;

import java.util.List;

/**
 * Created by keiji_ariyama on 4/14/16.
 */
public class Book {

    private final Align startPageAlign;

    public Align getStartPageAlign() {
        return startPageAlign;
    }

    private final List<Page> pageList;

    public List<Page> getPageList() {
        return pageList;
    }

    public Book(Align startPageAlign, List<Page> pageList) {
        this.startPageAlign = startPageAlign;
        this.pageList = pageList;
    }
}
