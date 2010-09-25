package com.gamecook.fit.collections;



import com.gamecook.fit.items.Item;
import com.gamecook.fit.items.MockItem;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 14, 2010
 * Time: 10:14:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setUp() throws Exception {
        inventory = new Inventory(100);

        // Item A
        Item itemA = new MockItem("Item A");
        itemA.setMinPrice(1);
        itemA.setMaxPrice(10);
        inventory.add(itemA, 1);

        // Item B
        Item itemB = new MockItem("Item B");
        itemB.setMinPrice(10);
        itemB.setMaxPrice(20);
        inventory.add(itemB, 10);

        // Item C
        Item itemC = new MockItem("Item C");
        itemC.setMinPrice(20);
        itemC.setMaxPrice(30);
        inventory.add(itemC, 4);
    }

    @Test
    public void testAdd() throws Exception {
        Item itemD = new MockItem("Item D");

        inventory.add(itemD, 1);

        Item itemDInStore = inventory.get("Item D");

        assertEquals(itemD, itemDInStore);
        assertEquals(itemDInStore.getTotal(), 1);
    }

    @Test
    public void testRemove() throws Exception {
        assertTrue(inventory.remove("Item B"));
    }

    @Test
    public void testRemoveFail() throws Exception {
        assertFalse(inventory.remove("Item X"));
    }

    @Test
    public void testGetItem()
    {
        assertNotNull(inventory.get("Item A"));
    }

    @Test
    public void testGetTotalItems()
    {
        assertEquals(inventory.getTotalItems(), 3);
    }

    @Test
    public void testGetItemTotal()
    {
        assertEquals(inventory.getItemTotal("Item B"), 10);
    }

    @Test
    public void testAddToItemTotal()
    {
        assertEquals(inventory.addToItemTotal("Item B", 20), 30);

    }

    @Test
    public void testAddItemThatExists()
    {
        Item tmpItem = new MockItem("Item B");
        inventory.setMaxTotal(-1);
        inventory.add(tmpItem, 100);
        assertEquals(inventory.get("Item B").getTotal(), 110);
    }

    @Test
    public void testRemoveItemIsNull()
    {
        Item tmpItem = new MockItem("FooBar");
        inventory.add(tmpItem, 10);
        inventory.removeFromInventory(tmpItem, 10);
        assertNull(inventory.get("FooBar"));
    }

    @Test
    public void testRemoveFromInventory()
    {
        Item tmpItem = new MockItem("FooBar");
        inventory.add(tmpItem, 10);
        assertEquals(inventory.removeFromInventory(tmpItem, 5), 5);
        assertEquals(inventory.getItemTotal("FooBar"), 5);
    }

    @org.junit.Test(expected=Error.class)
    public void testMaxInventory()
    {
        Item tmpItem = new MockItem("FooBar");
        inventory.add(tmpItem, 200);
    }

    @Test
    public void testCurrentTotal()
    {
        Item tmpItem = new MockItem("FooBar");
        inventory.add(tmpItem, 10);

        Item tmpItem2 = new MockItem("FooBar2");
        inventory.add(tmpItem2, 10);

        Item tmpItem3 = new MockItem("FooBar3");
        inventory.add(tmpItem3, 10);

        assertEquals(inventory.getCurrentTotal(), 45);
    }

    @Test
    public void testMaxTotalIgnore()
    {
        Inventory tmpInventory = new Inventory(-1);

        Item tmpItem = new MockItem("FooBar");
        tmpInventory.add(tmpItem, 100);

        Item tmpItem2 = new MockItem("FooBar2");
        tmpInventory.add(tmpItem2, 100);

        Item tmpItem3 = new MockItem("FooBar3");
        tmpInventory.add(tmpItem3, 100);

        assertEquals(tmpInventory.getCurrentTotal(), 0);
    }

    @org.junit.Test(expected=Error.class)
    public void testSetMaxTotalLowerThenCurrentTotal()
    {
        Inventory tmpInventory = new Inventory(100);

        Item tmpItem = new MockItem("FooBar");
        tmpInventory.add(tmpItem, 100);

        tmpInventory.setMaxTotal(50);
    }

    @org.junit.Test(expected=Error.class)
    public void testSetMaxTotalLowerThenCurrentTotalAtZero()
    {
        Inventory tmpInventory = new Inventory(100);

        Item tmpItem = new MockItem("FooBar");
        tmpInventory.add(tmpItem, 50);

        tmpInventory.setMaxTotal(0);
    }

    @Test
    public void testTotalLeft()
    {
        assertEquals(inventory.getTotalLeft(), 85);
    }
}
