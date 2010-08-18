package com.gamecook.fit.collections;

import org.junit.Before;
import org.junit.Test;

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

    public InventoryTest()
    {
        
    }

    @Before
    public void createInventory()
    {
        inventory = new Inventory();
    }

    @Test
    public void testTotalItemsIsZero()
    {
        assertEquals(inventory.getTotal(), 0);
    }

    @Test
    public void testTotalItems()
    {
        inventory.addToInventory("foo", 30);
        inventory.addToInventory("bar", 20);

        assertEquals(inventory.getTotal(), 2);
    }

    @Test
    public void testRemoveFromInventoryItem()
    {
        inventory.addToInventory("foobar", 20);
        inventory.removeFromInventory("foobar", 20);

        assertEquals(inventory.getTotal(), 0);
    }

    @org.junit.Test(expected=Error.class)
    public void testRemoveFromInventoryItemOutOfRange()
    {
        inventory.addToInventory("foobar", 20);
        inventory.removeFromInventory("foobar", 21);
    }

    @Test
    public void testAddToInventoryItem()
    {
        inventory.addToInventory("foobar", 20);
        assertEquals(inventory.getTotalOfItem("foobar"), 20);
    }

    @Test
    public void testAddToInventoryOntoItem()
    {
        inventory.addToInventory("foobar", 20);
        inventory.addToInventory("foobar", 30);
        assertEquals(inventory.getTotalOfItem("foobar"), 50);
    }

    @Test
    public void testRemoveFromInventoryFromItem()
    {
        inventory.addToInventory("foobar", 20);
        inventory.removeFromInventory("foobar", 10);
        assertEquals(inventory.getTotalOfItem("foobar"), 10);
    }
}
