package com.gamecook.fit.collections;

import com.gamecook.fit.items.AbstractItem;
import com.gamecook.fit.items.Item;
import com.gamecook.fit.items.MockItem;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 16, 2010
 * Time: 3:44:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class StoreTest {
    private Store store;

    @Before
    public void setUp() throws Exception {
        store = new Store();

        // Item A
        Item itemA = new MockItem("Item A");
        itemA.setMinPrice(1);
        itemA.setMaxPrice(10);
        store.add(itemA, 1);

        // Item B
        Item itemB = new MockItem("Item B");
        itemB.setMinPrice(10);
        itemB.setMaxPrice(20);
        store.add(itemB, 10);

        // Item C
        Item itemC = new MockItem("Item C");
        itemC.setMinPrice(20);
        itemC.setMaxPrice(30);
        store.add(itemC, 4);
    }

    @Test
    public void testRefresh() throws Exception {

        store.refresh();

        Item itemA = store.get("Item A");
        assertTrue(itemA.getPrice() <= itemA.getMaxPrice() && itemA.getPrice() >= itemA.getMinPrice());
    }

}