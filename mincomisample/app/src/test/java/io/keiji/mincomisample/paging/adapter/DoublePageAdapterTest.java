package io.keiji.mincomisample.paging.adapter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.keiji.mincomisample.paging.entity.Align;
import io.keiji.mincomisample.paging.entity.Book;
import io.keiji.mincomisample.paging.entity.Page;
import io.keiji.mincomisample.paging.entity.PageContainer;

public class DoublePageAdapterTest {

    private static Book createBook1() {
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

        return new Book(Align.Left, pageList);
    }

    private static Book createBook2() {
        List<Page> pageList = new ArrayList<>();
        pageList.add(new Page(1, false, "page1.png"));
        pageList.add(new Page(2, false, "page2.png"));
        pageList.add(new Page(3, false, "page3.png"));
        pageList.add(new Page(4, false, "page4.png"));
        pageList.add(new Page(5, false, "page5.png"));
        pageList.add(new Page(6, false, "page6.png"));
        pageList.add(new Page(7, false, "page7.png"));
        pageList.add(new Page(8, false, "page8.png"));
        pageList.add(new Page(9, true, "page9.png"));
        pageList.add(new Page(10, true, "page10.png"));
        pageList.add(new Page(11, false, "page11.png"));
        pageList.add(new Page(12, false, "page12.png"));
        pageList.add(new Page(13, false, "page13.png"));
        pageList.add(new Page(14, false, "page14.png"));
        pageList.add(new Page(15, false, "page15.png"));

        return new Book(Align.Right, pageList);
    }

    @Test
    public void testBook1() throws Exception {
        DoublePageAdapter adapter = new DoublePageAdapter(createBook1());

        PageContainer pageContainer = adapter.getPageInternal(0);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertEquals(1, pageContainer.left.getNumber());
        Assert.assertNull(pageContainer.center);
        Assert.assertNull(pageContainer.right);

        pageContainer = adapter.getPageInternal(1);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(2, pageContainer.right.getNumber());
        Assert.assertEquals(3, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(2);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(4, pageContainer.right.getNumber());
        Assert.assertEquals(5, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(3);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(6, pageContainer.right.getNumber());
        Assert.assertEquals(7, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(4);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(8, pageContainer.right.getNumber());
        Assert.assertEquals(9, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(5);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(10, pageContainer.right.getNumber());
        Assert.assertEquals(11, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(6);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(12, pageContainer.right.getNumber());
        Assert.assertEquals(13, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(7);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(14, pageContainer.right.getNumber());
        Assert.assertEquals(15, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(8);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(16, pageContainer.right.getNumber());
    }

    @Test
    public void testBook2() throws Exception {
        DoublePageAdapter adapter = new DoublePageAdapter(createBook2());

        PageContainer pageContainer = adapter.getPageInternal(0);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(1, pageContainer.right.getNumber());
        Assert.assertEquals(2, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(1);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(3, pageContainer.right.getNumber());
        Assert.assertEquals(4, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(2);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(5, pageContainer.right.getNumber());
        Assert.assertEquals(6, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(3);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(7, pageContainer.right.getNumber());
        Assert.assertEquals(8, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(4);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(9, pageContainer.right.getNumber());
        Assert.assertEquals(10, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(5);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(11, pageContainer.right.getNumber());
        Assert.assertEquals(12, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(6);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNotNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(13, pageContainer.right.getNumber());
        Assert.assertEquals(14, pageContainer.left.getNumber());

        pageContainer = adapter.getPageInternal(7);
        Assert.assertNotNull(pageContainer.right);
        Assert.assertNull(pageContainer.left);
        Assert.assertNull(pageContainer.center);
        Assert.assertEquals(15, pageContainer.right.getNumber());
    }
}
