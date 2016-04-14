package io.keiji.mincomisample.paging.adapter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.keiji.mincomisample.paging.entity.Align;
import io.keiji.mincomisample.paging.entity.Book;
import io.keiji.mincomisample.paging.entity.Page;
import io.keiji.mincomisample.paging.entity.PageContainer;

public class SinglePageAdapterTest {

    private Book createBook() {
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

        return new Book(Align.Left, pageList);
    }

    @Test
    public void test1() throws Exception {
        SinglePageAdapter adapter = new SinglePageAdapter(createBook());

        PageContainer pageContainer = adapter.getPageInternal(0);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(1, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(1);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(2, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(2);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(3, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(3);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(4, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(4);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(5, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(5);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(6, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(6);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(7, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(7);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertEquals(8, pageContainer.right.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);

        pageContainer = adapter.getPageInternal(8);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertEquals(9, pageContainer.left.getNumber());
        Assert.assertNull(pageContainer.center);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(9);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(10, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(10);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(11, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(11);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(12, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(12);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(13, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(13);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(14, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(14);
        Assert.assertNotNull(pageContainer.center);
        Assert.assertEquals(15, pageContainer.center.getNumber());
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.right);

    }
}
