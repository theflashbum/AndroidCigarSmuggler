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
 * Date: Aug 14, 2010
 * Time: 10:14:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryTest {

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

    @Test
    public void testAdd() throws Exception {
        Item itemD = new MockItem("Item D");

        store.add(itemD, 1);

        Item itemDInStore = store.get("Item D");

        assertEquals(itemD, itemDInStore);
        assertEquals(itemDInStore.getTotal(), 1);
    }

    @Test
    public void testRemove() throws Exception {
        assertTrue(store.remove("Item B"));
    }

    @Test
    public void testRemoveFail() throws Exception {
        assertFalse(store.remove("Item X"));
    }

    @Test
    public void testGetItem()
    {
        assertNotNull(store.get("Item A"));
    }

    @Test
    public void testGetTotalItems()
    {
        assertEquals(store.getTotalItems(), 3);
    }

    @Test
    public void testGetItemTotal()
    {
        assertEquals(store.getItemTotal("Item B"), 10);
    }

    @Test
    public void testAddToItemTotal()
    {
        assertEquals(store.addToItemTotal("Item B", 20), 30);

    }

    @Test
    public void testAddItemThatExists()
    {
        Item tmpItem = new MockItem("Item B");
        store.add(tmpItem, 100);
        assertEquals(store.get("Item B").getTotal(), 110);
    }

    @Test
    public void testRemoveItemIsNull()
    {
        Item tmpItem = new MockItem("FooBar");
        store.add(tmpItem, 10);
        store.removeFromInventory(tmpItem, 10);
        assertNull(store.get("FooBar"));
    }

    @Test
    public void testRemoveFromInventory()
    {
        Item tmpItem = new MockItem("FooBar");
        store.add(tmpItem, 10);
        assertEquals(store.removeFromInventory(tmpItem, 5), 5);
        assertEquals(store.getItemTotal("FooBar"), 5);
    }
}
